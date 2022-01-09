package ar.com.frupp.gpuroi.controller;

import ar.com.frupp.gpuroi.entity.Price;
import ar.com.frupp.gpuroi.entity.PriceTypes;
import ar.com.frupp.gpuroi.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prices")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PriceController {

    private final PriceService service;

    @GetMapping("/{type}")
    public Price getPrice(@PathVariable("type") PriceTypes type) {
        return this.service.findByType(type);
    }

    @PostMapping
    public void synchronize() {
        new Thread(this.service::sync).start();
    }
}
