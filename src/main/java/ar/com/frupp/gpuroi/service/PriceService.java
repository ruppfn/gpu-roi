package ar.com.frupp.gpuroi.service;

import ar.com.frupp.gpuroi.entity.Price;
import ar.com.frupp.gpuroi.entity.PriceTypes;

public interface PriceService extends Synchronizable{
    Price findByType(PriceTypes type);
    void updateUsd();
}
