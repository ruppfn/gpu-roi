package ar.com.frupp.gpuroi.controller;

import ar.com.frupp.gpuroi.model.DeviceJson;
import ar.com.frupp.gpuroi.model.Paginated;
import ar.com.frupp.gpuroi.model.UpdatePriceRequest;
import ar.com.frupp.gpuroi.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/devices")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping
    public Paginated<DeviceJson> getDevices(@RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        return this.deviceService.findAll(pageNumber);
    }

    @GetMapping("/withoutPrice")
    public Paginated<DeviceJson> getDevicesWithoutPrice(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        return this.deviceService.findAllWithoutPrice(pageNumber);
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
}
