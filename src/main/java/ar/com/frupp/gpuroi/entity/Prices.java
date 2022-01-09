package ar.com.frupp.gpuroi.entity;

import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity @Table(name = "PRICES")
@Getter
public class Prices {

    public static final String USD_NAME = "USD";

    @Id @NonNull
    private String name;

    @NonNull
    private BigDecimal price;

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
