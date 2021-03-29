package com.acme.mytrader.strategy;

import static org.junit.Assert.fail;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceSource;
import com.acme.mytrader.price.impl.PriceListenerImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TradingStrategyTest {
	@InjectMocks
	TradingStrategy tradingStrategy;
	
	@Mock
	PriceSource priceSource;
	@Mock
	ExecutionService executionService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		tradingStrategy = new TradingStrategy();
	}

	@Test
	public void testProcessStockAddListenerSuccess() {
		try {
			tradingStrategy.processStock(new PriceListenerImpl(55,100), "IBM",20);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testProcessStockSecEmptyInputValidity() {
		try {
			tradingStrategy.processStock(new PriceListenerImpl(55,100), "",20);
			fail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testProcessStockNegPriceInputValidity() {
		try {
			tradingStrategy.processStock(new PriceListenerImpl(55,100), "IBM",-20);
			fail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = Exception.class)
	public void testProcessStockFail() throws Exception {
		tradingStrategy.processStock(new PriceListenerImpl(55,100), "IBM",20);
	}
	
}
