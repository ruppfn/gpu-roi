package ar.com.frupp.gpuroi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class DeviceJson {
    private static final String GPU_CATEGORY = "GPU";

    private String id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String category;

    private Integer power;
    private Map<String, String> speeds;
    private BigDecimal paying;

    private BigDecimal priceInArs;
    private BigDecimal daysToROI;

    @JsonIgnore
    public boolean isGpu() {
        return this.category.equals(GPU_CATEGORY);
    }

}
