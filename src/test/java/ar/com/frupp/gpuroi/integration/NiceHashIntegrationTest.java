package ar.com.frupp.gpuroi.integration;

import ar.com.frupp.gpuroi.interactor.NiceHashInteractor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class NiceHashIntegrationTest {

    @Autowired
    private NiceHashInteractor interactor;

    @Test
    void getDevicesShouldFindDevices() {
        var devices = this.interactor.findDevices();

        assertFalse(devices.isEmpty());
    }

}
