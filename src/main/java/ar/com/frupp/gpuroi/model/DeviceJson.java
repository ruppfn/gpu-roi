package ar.com.frupp.gpuroi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @ToString
public class DeviceJson {
    private static final String GPU_CATEGORY = "GPU";

    private String id;
    private String name;
    private String category;
    private Integer power;
    private Map<String, String> speeds;
    private BigDecimal paying;

    public boolean isGpu() {
        return this.category.equals(GPU_CATEGORY);
    }

}
