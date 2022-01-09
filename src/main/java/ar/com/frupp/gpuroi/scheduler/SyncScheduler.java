package ar.com.frupp.gpuroi.scheduler;

import ar.com.frupp.gpuroi.service.DeviceService;
import ar.com.frupp.gpuroi.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SyncScheduler {

    private final DeviceService deviceService;
    private final PriceService priceService;

    @Scheduled(cron = "@hourly")
    public void syncDevices() {
        this.deviceService.sync();
    }

    @Scheduled(cron = "@hourly")
    public void syncPrices() {
        this.priceService.sync();
    }
}
