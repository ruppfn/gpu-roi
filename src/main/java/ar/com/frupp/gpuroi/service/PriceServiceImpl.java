package ar.com.frupp.gpuroi.service;

import ar.com.frupp.gpuroi.entity.Price;
import ar.com.frupp.gpuroi.entity.PriceTypes;
import ar.com.frupp.gpuroi.interactor.BinanceInteractor;
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
    private final BinanceInteractor binanceInteractor;

    @Override
    public Price findByType(PriceTypes type) {
        this.logger.info("Finding USD Price");
        var optional = this.repository.findByType(type);

        if (optional.isEmpty()) {
            this.logger.error("Couldn't find Type");
            //TODO: Make custom exception
            throw new RuntimeException("Couldn't find USD Price");
        }

        return optional.get();
    }

    @Override
    public void updateUsd() {
        this.logger.info("Updating USD Price");

        var value = this.usdInteractor.getUsdValue();

        this.repository.save(new Price(PriceTypes.USD, value));

        this.logger.info("USD Price updated");
    }

    @Override
    public void updateBtc() {
        this.logger.info("Updating BTC Price");

        var value = this.binanceInteractor.getBtcValue();
        this.repository.save(new Price(PriceTypes.BTC, value));

        this.logger.info("BTC Price updated");
    }

    @Override
    public void sync() {
        this.logger.info("Starting Prices synchronization");
        updateUsd();
        updateBtc();
        this.logger.info("Prices synchronization finished");
    }
}
