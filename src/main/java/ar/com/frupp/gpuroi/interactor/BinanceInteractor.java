package ar.com.frupp.gpuroi.interactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class BinanceInteractor {

    private final Logger logger = LoggerFactory.getLogger(BinanceInteractor.class);

    private final String btcEndpoint;
    private final RestTemplate restTemplate;

    public BinanceInteractor(@Value("${app.endpoints.binance}") String btcEndpoint,
                             RestTemplate restTemplate) {
        this.btcEndpoint = btcEndpoint;
        this.restTemplate = restTemplate;
    }

    public BigDecimal getBtcValue() {
        this.logger.debug("Calling BTC Endpoint");

        var response = this.restTemplate.getForEntity(btcEndpoint, BinanceResponse.class);

        this.logger.debug("Response status code {}", response.getStatusCodeValue());

        if (!response.getStatusCode().is2xxSuccessful()) {
            //TODO: Make custom exception
            this.logger.error("Couldn't get BTC Value");
            throw new RuntimeException("Couldn't get BTC Value");
        }

        var value = new BigDecimal(response.getBody().price());

        this.logger.debug("BTC Value: {}", value);

        return value;
    }

    private static record BinanceResponse(Integer mins, String price){}
}
