package ar.com.frupp.gpuroi.mapper;

import ar.com.frupp.gpuroi.entity.Device;
import ar.com.frupp.gpuroi.model.DeviceJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.stream.Collectors;

public class DeviceMapper {

    private final static Logger logger = LoggerFactory.getLogger(DeviceMapper.class);

    public static Device toEntity(DeviceJson model) {
        logger.debug("Mapping DeviceJson to entity. {}", model);

        var entity = new Device(
                model.getId(), model.getName(), model.getPower(), model.getPaying()
        );

        logger.debug("Entity generated: {}", entity);

        var speeds = model.getSpeeds().entrySet().stream().filter(
                map -> !Objects.equals(map.getValue(), "0")
        ).map(
                map -> DeviceSpeedMapper.toEntity(map.getKey(), map.getValue(), entity)
        ).collect(Collectors.toSet());

        speeds.forEach(entity::addSpeed);

        return entity;
    }

    public static DeviceJson toModel(Device entity) {
        logger.debug("Mapping Device to model. {}", entity);

        var model = new DeviceJson(
                entity.getId(), entity.getName(), null, entity.getPower(),
                DeviceSpeedMapper.toModel(entity.getSpeeds()), entity.getPaying(),
                entity.getPriceInArs(), entity.getDaysToROI()
        );

        return model;
    }
}
