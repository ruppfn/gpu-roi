package ar.com.frupp.gpuroi.controller;

import ar.com.frupp.gpuroi.entity.Price;
import ar.com.frupp.gpuroi.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prices")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PriceController {

    private final PriceService service;

    @GetMapping("/usd")
    public Price getUsdPrice() {
        return this.service.findUsd();
    }

    @PostMapping
    public void synchronize() {
        new Thread(this.service::sync).start();
    }
}
