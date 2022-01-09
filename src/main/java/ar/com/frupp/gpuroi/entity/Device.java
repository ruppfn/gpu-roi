package ar.com.frupp.gpuroi.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "DEVICES")
@RequiredArgsConstructor
@NoArgsConstructor @Getter @ToString
public class Device {

    @NonNull
    @Id @Column(name = "DEVICE_ID")
    private String id;

    @NonNull
    @Column(name = "NAME")
    private String name;

    @NonNull
    @Column(name = "POWER")
    private Integer power;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "device")
    private Set<DeviceSpeed> speeds;

    @NonNull
    @Column(name = "PAYING", precision = 10, scale = 8)
    private BigDecimal paying;

    @Column(name = "PRICE_IN_ARS", precision = 8, scale = 0)
    private BigDecimal priceInArs;

    @Column(name = "DAYS_TO_ROI", precision = 6, scale = 2)
    private BigDecimal daysToROI;

    public void addSpeed(DeviceSpeed speeds) {
        if (this.speeds == null) {
            this.speeds = new HashSet<>();
        }
        this.speeds.add(speeds);
    }

    public void setPaying(BigDecimal paying) {
        this.paying = paying;
    }

    public void setPriceInArs(BigDecimal priceInArs) {
        this.priceInArs = priceInArs;
    }

    public void setDaysToROI(BigDecimal daysToROI) {
        this.daysToROI = daysToROI;
    }

}
