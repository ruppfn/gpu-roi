package ar.com.frupp.gpuroi.config;

import ar.com.frupp.gpuroi.entity.PriceTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

class StringToPriceTypeConverter implements Converter<String, PriceTypes> {
    private final Logger logger = LoggerFactory.getLogger(StringToPriceTypeConverter.class);
    @Override
    public PriceTypes convert(String source) {
        try {
            return PriceTypes.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            this.logger.error("Couldn't find Price Type {}", source);
            return null;
        }
    }
}
