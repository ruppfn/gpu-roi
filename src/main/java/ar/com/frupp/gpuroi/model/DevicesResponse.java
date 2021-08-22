package ar.com.frupp.gpuroi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class DevicesResponse {
    private Collection<DeviceJson> devices;
}
