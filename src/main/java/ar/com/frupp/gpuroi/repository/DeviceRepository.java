package ar.com.frupp.gpuroi.repository;

import ar.com.frupp.gpuroi.entity.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DeviceRepository extends PagingAndSortingRepository<Device, String> {
    Page<Device> findAllByPriceInArsIsNull(Pageable pageable);
}
