package ar.com.frupp.gpuroi.service;

import ar.com.frupp.gpuroi.entity.Device;
import ar.com.frupp.gpuroi.entity.PriceTypes;
import ar.com.frupp.gpuroi.interactor.NiceHashInteractor;
import ar.com.frupp.gpuroi.mapper.DeviceMapper;
import ar.com.frupp.gpuroi.model.DeviceJson;
import ar.com.frupp.gpuroi.model.Paginated;
import ar.com.frupp.gpuroi.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private static final Integer PAGE_SIZE = 10;
    private static final String SORT_BY_COLUMN = "paying";

    private final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    private final DeviceRepository repository;
    private final NiceHashInteractor niceHashInteractor;

    private final PriceService priceService;

    @Override
    public Paginated<DeviceJson> findAll(Integer pageNumber) {
        this.logger.info("Finding Devices page {}", pageNumber);

        var sort = Sort.by(Sort.Direction.DESC, SORT_BY_COLUMN);
        var pageRequest = PageRequest.of(pageNumber, PAGE_SIZE, sort);
        var page = this.repository.findAll(pageRequest);

        var jsonList = page.stream().map(DeviceMapper::toModel).collect(Collectors.toList());
        return new Paginated<>(page.getNumber(), page.getTotalElements(), jsonList);
    }

    @Override
    public Collection<DeviceJson> findAllWithoutPrice() {
        this.logger.info("Finding Devices without price");

        var devices = this.repository.findAllByPriceInArsIsNull();

        this.logger.debug("Found {} devices", devices.size());

        return devices.stream().map(DeviceMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public DeviceJson updatePriceAndROI(String deviceId, BigDecimal priceInArs) {
        this.logger.info("Adding price ({}) and ROI for Device id {}", priceInArs, deviceId);
        var optional = this.repository.findById(deviceId);

        if(optional.isEmpty()) {
            this.logger.info("Device with id {} not found", deviceId);
            return new DeviceJson();
        }

        final var device = optional.get();
        final var roi = calculateRoi(device, priceInArs);

        device.setPriceInArs(priceInArs);
        device.setDaysToROI(roi);
        this.repository.save(device);

        this.logger.debug("Price and ROI saved");

        return DeviceMapper.toModel(device);
    }

    private BigDecimal calculateRoi(Device device, BigDecimal priceInArs) {
        final var usdPrice = getUsdPrice();
        final var btcPrice = getBtcPrice();
        final var btcInArs = btcPrice.multiply(usdPrice);

        final var profitInArs = device.getPaying().multiply(btcInArs);

        if (profitInArs.intValue() == 0 || priceInArs.intValue() == 0) return new BigDecimal(0);

        return priceInArs.divide(profitInArs, 2, RoundingMode.HALF_UP);
    }

    private BigDecimal getBtcPrice() {
        return this.priceService.findByType(PriceTypes.BTC).getPrice();
    }

    private BigDecimal getUsdPrice() {
        return this.priceService.findByType(PriceTypes.USD).getPrice();
    }

    @Override
    public void save(Device device) {
        this.repository.save(device);
    }

    @Override
    public void sync() {
        this.logger.info("Starting devices synchronization");

        var savedDevicesMap = new HashMap<String, Device>();

        this.repository.findAll().forEach(device -> savedDevicesMap.put(device.getId(), device));

        var updatedDevices = this.niceHashInteractor.findDevices().stream().filter(DeviceJson::isGpu)
                .map(DeviceMapper::toEntity).collect(Collectors.toSet());

        this.logger.debug("Found {} devices", updatedDevices.size());

        for (Device updatedDevice: updatedDevices) {
            var savedDevice = savedDevicesMap.get(updatedDevice.getId());
            savedDevice.setPaying(updatedDevice.getPaying());
            if (savedDevice.getPriceInArs() != null) {
                this.logger.debug("Updating ROI for device id {}", savedDevice.getId());
                savedDevice.setDaysToROI(calculateRoi(savedDevice, savedDevice.getPriceInArs()));
            }
        }

        this.repository.saveAll(savedDevicesMap.values());

        this.logger.info("Devices synchronization finished");
    }
}
