package ar.com.frupp.gpuroi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DEVICES_SPEEDS")
@AllArgsConstructor @NoArgsConstructor @Getter @ToString
public class DeviceSpeed implements Serializable {

    @Id @Column(name = "DEVICE_SPEED_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne @JoinColumn(name = "DEVICE_ID")
    @ToString.Exclude
    private Device device;

    @Column(name = "ALGORITHM")
    private String algorithm;

    @Column(name = "SPEED")
    private Double speed;

    public DeviceSpeed(Device device, String algorithm, Double speed) {
        this.device = device;
        this.algorithm = algorithm;
        this.speed = speed;
    }

}
