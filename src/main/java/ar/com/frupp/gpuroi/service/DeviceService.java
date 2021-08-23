package ar.com.frupp.gpuroi.service;

import ar.com.frupp.gpuroi.entity.Device;
import org.springframework.data.domain.Page;

public interface DeviceService {

    Page<Device> findAll(Integer pageNumber);

    void save(Device device);

    void sync();

}
