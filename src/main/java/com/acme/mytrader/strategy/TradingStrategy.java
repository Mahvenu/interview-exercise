package com.acme.mytrader.strategy;

import com.acme.mytrader.price.PriceSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.acme.mytrader.price.PriceListener;

import java.util.List;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they reach a trigger level orders can be executed automatically
 * </pre>
 *
 * @author Mahesh
 */
public class TradingStrategy {

    @Autowired
    PriceSource priceSource;

    public void processStock(PriceListener priceListener, String security, double price) throws Exception {
        if((null != security && !security.isEmpty()) && price > 0) {
            priceSource.addPriceListener(priceListener);
            priceListener.priceUpdate(security, price);
            priceSource.removePriceListener(priceListener);
        } else {
            throw new Exception("Invalid input:: Either Security is empty or price is negative \n security:" +security +" \n price: "+price);
        }
    }

}
