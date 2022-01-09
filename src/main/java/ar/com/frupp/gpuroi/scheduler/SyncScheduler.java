package ar.com.frupp.gpuroi.scheduler;

import ar.com.frupp.gpuroi.scraper.GpuPriceScraper;
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
    private final GpuPriceScraper gpuScraper;

    @Scheduled(cron = "@hourly")
    public void syncDevices() {
        this.deviceService.sync();
    }

    @Scheduled(cron = "@hourly")
    public void syncPrices() {
        this.priceService.sync();
    }

    @Scheduled(cron = "@daily")
    public void syncDevicePrices() {
        this.gpuScraper.scrape();
    }
}
