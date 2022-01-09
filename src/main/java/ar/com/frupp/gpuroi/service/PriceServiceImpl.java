package ar.com.frupp.gpuroi.service;

import ar.com.frupp.gpuroi.entity.Price;
import ar.com.frupp.gpuroi.interactor.UsdInteractor;
import ar.com.frupp.gpuroi.repository.PriceRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);

    private final PriceRepository repository;
    private final UsdInteractor usdInteractor;

    @Override
    public Price findUsd() {
        this.logger.info("Finding USD Price");
        var optional = this.repository.findByName(Price.USD_NAME);

        if (optional.isEmpty()) {
            this.logger.error("Couldn't find USD Price");
            //TODO: Make custom exception
            throw new RuntimeException("Couldn't find USD Price");
        }

        return optional.get();
    }

    @Override
    public void updateUsd() {
        this.logger.info("Updating USD Price");

        var value = this.usdInteractor.getUsdValue();

        this.repository.save(new Price(Price.USD_NAME, value));

        this.logger.info("USD Price updated");
    }

    @Override
    public void sync() {
        this.logger.info("Starting Prices synchronization");
        updateUsd();
        this.logger.info("Prices synchronization finished");
    }
}
