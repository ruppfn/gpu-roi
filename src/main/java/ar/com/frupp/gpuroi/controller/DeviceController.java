package ar.com.frupp.gpuroi.controller;

import ar.com.frupp.gpuroi.model.DeviceJson;
import ar.com.frupp.gpuroi.model.Paginated;
import ar.com.frupp.gpuroi.model.UpdatePriceRequest;
import ar.com.frupp.gpuroi.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/devices")
@AllArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping
    public Paginated<DeviceJson> getDevices(@RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        return this.deviceService.findAll(pageNumber);
    }

    @PutMapping
    public DeviceJson updatePrice(@RequestBody UpdatePriceRequest updatePriceRequest) {
        return this.deviceService.updatePriceAndROI(
                updatePriceRequest.deviceId(), updatePriceRequest.price(), updatePriceRequest.daysToROI()
        );
    }

    @PostMapping
    public void synchronize() {
       new Thread(this.deviceService::sync).start();
    }
}
