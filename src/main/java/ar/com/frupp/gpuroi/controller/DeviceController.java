package ar.com.frupp.gpuroi.controller;

import ar.com.frupp.gpuroi.model.DeviceJson;
import ar.com.frupp.gpuroi.model.Paginated;
import ar.com.frupp.gpuroi.model.UpdatePriceRequest;
import ar.com.frupp.gpuroi.scraper.GpuPriceScraper;
import ar.com.frupp.gpuroi.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/api/devices")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DeviceController {

    private final DeviceService deviceService;
    private final GpuPriceScraper priceScraper;

    @GetMapping
    public Paginated<DeviceJson> getDevices(@RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        return this.deviceService.findAll(pageNumber);
    }

    @GetMapping("/withoutPrice")
    public Collection<DeviceJson> getDevicesWithoutPrice() {
        return this.deviceService.findAllToUpdatePrice();
    }

    @PutMapping
    public DeviceJson updatePrice(@RequestBody UpdatePriceRequest updatePriceRequest) {
        return this.deviceService.updatePriceAndROI(
                updatePriceRequest.deviceId(), updatePriceRequest.price()
        );
    }

    @PostMapping
    public void synchronize() {
       new Thread(this.deviceService::sync).start();
    }

    @PostMapping("/prices")
    public void synchronizeDevicePrices() {
        new Thread(this.priceScraper::scrape).start();
    }
}
