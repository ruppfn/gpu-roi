package ar.com.frupp.gpuroi.model;

import java.math.BigDecimal;

public record UpdatePriceRequest(String deviceId, BigDecimal price) {
}
