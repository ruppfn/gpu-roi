package ar.com.frupp.gpuroi.integration;

import ar.com.frupp.gpuroi.interactor.BinanceInteractor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BinanceIntegrationTest {

    @Autowired
    private BinanceInteractor binanceInteractor;

    @Test
    void shouldGetValue() {
        var value = binanceInteractor.getBtcValue();
        assertNotNull(value);
    }
}
