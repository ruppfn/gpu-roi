package ar.com.frupp.gpuroi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity @Table(name = "PRICES")
@Getter
@AllArgsConstructor @NoArgsConstructor
public class Price {

    @Id @Enumerated(EnumType.STRING)
    private PriceTypes type;

    @NonNull
    private BigDecimal price;

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
