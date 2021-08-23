package ar.com.frupp.gpuroi.controller;

import ar.com.frupp.gpuroi.entity.Device;
import ar.com.frupp.gpuroi.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/devices")
@AllArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping
    public Collection<Device> getDevices(@RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        return null;
    }

    @PostMapping
    public void synchronize() {
       new Thread(this.deviceService::sync).start();
    }
}
