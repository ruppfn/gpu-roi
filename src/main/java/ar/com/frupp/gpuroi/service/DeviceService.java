package ar.com.frupp.gpuroi.service;

import ar.com.frupp.gpuroi.entity.Device;
import ar.com.frupp.gpuroi.model.DeviceJson;
import ar.com.frupp.gpuroi.model.Paginated;

import java.math.BigDecimal;

public interface DeviceService {

    Paginated<DeviceJson> findAll(Integer pageNumber);

    DeviceJson updatePriceAndROI(String deviceId, BigDecimal priceInArs, BigDecimal daysToROI);

    Paginated<DeviceJson> findAllWithoutPrice(Integer pageNumber);

    void save(Device device);

    void sync();

}
