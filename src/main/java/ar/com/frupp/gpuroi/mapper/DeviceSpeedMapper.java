package ar.com.frupp.gpuroi.mapper;

import ar.com.frupp.gpuroi.entity.Device;
import ar.com.frupp.gpuroi.entity.DeviceSpeed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeviceSpeedMapper {

    private final static Logger logger = LoggerFactory.getLogger(DeviceSpeedMapper.class);

    public static DeviceSpeed toEntity(String algorithm, String speedStr, Device device) {
        logger.debug("Mapping DeviceSpeed with algorithm: {}, speed: {}", algorithm, speedStr);

        Double speed = speedStr != null && speedStr.strip().length() > 0 ? Double.parseDouble(speedStr) : 0;
        var entity = new DeviceSpeed(device, algorithm, speed);

        logger.debug("Entity generated: {}", entity);

        return entity;
    }
}
