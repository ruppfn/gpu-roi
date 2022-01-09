package ar.com.frupp.gpuroi.interactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class UsdInteractor {

    private final Logger logger = LoggerFactory.getLogger(UsdInteractor.class);
    private final RestTemplate restTemplate;
    private final String usdEndpoint;

    public UsdInteractor(@Value("${app.endpoints.usd}") String usdEndpoint,
                         RestTemplate restTemplate) {
        this.logger.debug("Usd Endpoint: {}", usdEndpoint);

        this.restTemplate = restTemplate;
        this.usdEndpoint = usdEndpoint;
    }

    public BigDecimal getUsdValue() {
        this.logger.debug("Calling Usd endpoint");

        var response = this.restTemplate.getForEntity(this.usdEndpoint, UsdResponse.class);

        this.logger.debug("Response status code {}", response.getStatusCodeValue());

        if (!response.getStatusCode().is2xxSuccessful()) {
            //TODO: Make custom exception
            this.logger.error("Couldn't get Usd Value");
            throw new RuntimeException("Couldn't get Usd Value");
        }

        var value = new BigDecimal(response.getBody().compra());

        return value;
    }

    private static record UsdResponse(String fecha, String compra, String venta) {
    }
}
