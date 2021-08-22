package ar.com.frupp.gpuroi.interactor;

import ar.com.frupp.gpuroi.model.DeviceJson;
import ar.com.frupp.gpuroi.model.DevicesResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class NiceHashInteractor {

    private final Logger logger = LoggerFactory.getLogger(NiceHashInteractor.class);

    private final RestTemplate restTemplate;

    private final String devicesEndpoint;

    public NiceHashInteractor(@Value("${app.endpoints.devices}") String devicesEndpoint,
                              RestTemplate restTemplate) {
        this.logger.debug("NiceHash Devices Endpoint {}", devicesEndpoint);

        this.devicesEndpoint = devicesEndpoint;
        this.restTemplate = restTemplate;
    }

    public Collection<DeviceJson> findDevices() {
        this.logger.debug("Calling Devices endpoint");

        Collection<DeviceJson> devices = new ArrayList<>();

        var response = this.restTemplate.getForEntity(this.devicesEndpoint, String.class);

        this.logger.debug("Response status code {}", response.getStatusCodeValue());


        if (response.getStatusCode().is2xxSuccessful()) {
            devices = jsonToDevicesResponse(response.getBody()).getDevices();
        }

        return devices;
    }

    private DevicesResponse jsonToDevicesResponse(String json) {
        json = json.replace("\\\"", "\"");
        json = json.replace("\"{", "{");
        json = json.replace("}\"", "}");

        var mapper = new ObjectMapper();

        try {
            return mapper.readValue(json, DevicesResponse.class);
        } catch (JsonProcessingException e) {
            var devices = new DevicesResponse();
            devices.setDevices(new ArrayList<>());
            return devices;
        }
    }
}
