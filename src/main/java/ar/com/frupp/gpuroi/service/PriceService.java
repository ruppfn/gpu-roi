package ar.com.frupp.gpuroi.service;

import ar.com.frupp.gpuroi.entity.Price;

public interface PriceService extends Synchronizable{
    Price findUsd();
    void updateUsd();
}
