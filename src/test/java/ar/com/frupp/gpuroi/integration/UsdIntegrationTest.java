package ar.com.frupp.gpuroi.integration;

import ar.com.frupp.gpuroi.interactor.UsdInteractor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UsdIntegrationTest {

    @Autowired
    private UsdInteractor usdInteractor;

    @Test
    void shouldGetValue() {
        var value = this.usdInteractor.getUsdValue();

        assertNotNull(value);
    }
}
