package ar.com.frupp.gpuroi.scheduler;

import ar.com.frupp.gpuroi.service.DeviceService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SyncScheduler {

    private final DeviceService deviceService;

    public SyncScheduler(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Scheduled(cron = "@hourly")
    public void syncDevices() {
        this.deviceService.sync();
    }
}
