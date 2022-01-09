package ar.com.frupp.gpuroi.repository;

import ar.com.frupp.gpuroi.entity.Device;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

public interface DeviceRepository extends PagingAndSortingRepository<Device, String> {
    Collection<Device> findAllByPriceInArsIsNull();
}
