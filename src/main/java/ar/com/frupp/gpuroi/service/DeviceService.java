package ar.com.frupp.gpuroi.service;

import ar.com.frupp.gpuroi.entity.Device;
import ar.com.frupp.gpuroi.model.DeviceJson;
import ar.com.frupp.gpuroi.model.Paginated;

import java.math.BigDecimal;
import java.util.Collection;

public interface DeviceService extends Synchronizable {

    Paginated<DeviceJson> findAll(Integer pageNumber);

    DeviceJson updatePriceAndROI(String deviceId, BigDecimal priceInArs);

    Collection<DeviceJson> findAllToUpdatePrice();

    void save(Device device);

}
