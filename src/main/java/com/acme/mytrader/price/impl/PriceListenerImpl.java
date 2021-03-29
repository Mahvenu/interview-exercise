package com.acme.mytrader.price.impl;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;

@Service
public class PriceListenerImpl implements PriceListener {
    @Autowired
    PriceSource PriceSource;
    @Autowired
    ExecutionService executionService;
    double threshHold;
    int quantity;
    boolean isMatched = false;

    public PriceListenerImpl(double threshHold, int quantity) {
        this.threshHold = threshHold;
        this.quantity = quantity;
    }

    @Override
    public void priceUpdate(String security, double price) {
        boolean isMatchedStock = monitorStock(security, price);
        if (isMatchedStock) {
            executionService.buy(security, price, quantity);
            isMatched = true;
        }
    }

    public boolean monitorStock(String security, double price) {
        if (price > 0 && price <= threshHold) {
            return true;
        }
        return false;
    }
}
