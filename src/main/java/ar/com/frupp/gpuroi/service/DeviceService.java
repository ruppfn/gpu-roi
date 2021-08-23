package ar.com.frupp.gpuroi.service;

import ar.com.frupp.gpuroi.entity.Device;
import ar.com.frupp.gpuroi.model.DeviceJson;
import ar.com.frupp.gpuroi.model.Paginated;

public interface DeviceService {

    Paginated<DeviceJson> findAll(Integer pageNumber);

    void save(Device device);

    void sync();

}
