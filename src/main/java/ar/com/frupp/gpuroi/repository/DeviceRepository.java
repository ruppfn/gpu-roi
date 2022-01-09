package ar.com.frupp.gpuroi.repository;

import ar.com.frupp.gpuroi.entity.Device;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.Collection;

public interface DeviceRepository extends PagingAndSortingRepository<Device, String> {
    @Query("select d from Device d where d.lastUpdate is null or " +
            "d.lastUpdate <= ?#{@deviceRepository.yesterdayDateTime()}")
    Collection<Device> findAllWhereLastUpdateWasYesterdayOrOlder();

    default LocalDateTime yesterdayDateTime() {
        return LocalDateTime.now().minusDays(1);
    }
}
