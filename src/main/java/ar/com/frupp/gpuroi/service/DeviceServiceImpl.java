package ar.com.frupp.gpuroi.service;

import ar.com.frupp.gpuroi.entity.Device;
import ar.com.frupp.gpuroi.interactor.NiceHashInteractor;
import ar.com.frupp.gpuroi.mapper.DeviceMapper;
import ar.com.frupp.gpuroi.model.DeviceJson;
import ar.com.frupp.gpuroi.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private static final Integer PAGE_SIZE = 10;

    private final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    private final DeviceRepository repository;
    private final NiceHashInteractor niceHashInteractor;

    @Override
    public Page<Device> findAll(Integer pageNumber) {
        var page = PageRequest.of(pageNumber, PAGE_SIZE);
        return null;
    }

    @Override
    public void save(Device device) {
        this.repository.save(device);
    }

    @Override
    public void sync() {
        this.logger.info("Starting synchronization");

        var devices = this.niceHashInteractor.findDevices().stream().filter(DeviceJson::isGpu)
                .map(DeviceMapper::toEntity).collect(Collectors.toSet());

        this.logger.debug("Found {} devices", devices.size());

        this.repository.saveAll(devices);

        this.logger.info("Synchronization finished");
    }
}
