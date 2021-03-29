package com.acme.mytrader.price.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceSource;
import org.mockito.junit.MockitoJUnitRunner;

public class PriceListenerImplTest {

	@InjectMocks
	PriceListenerImpl priceListener=new PriceListenerImpl(55,100);;

	@Mock
	ExecutionService executionService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testPriceUpdateSuccessUnMatchedThreshhold() {
		doNothing().when(executionService).buy(anyString(),anyDouble(),anyInt());
		priceListener.priceUpdate("IBM", 100);
		assertEquals(false, priceListener.isMatched);
	}
	
	@Test
	public void testPriceUpdateMatchedThreshhold() {
		doNothing().when(executionService).buy(anyString(),anyDouble(),anyInt());
		priceListener.priceUpdate("IBM", 54);
		assertEquals(true, priceListener.isMatched);
		
	}

	@Test(expected = Exception.class)
	public void testPriceUpdateFailException() {
		priceListener.priceUpdate("IBM", 54);
		doThrow(Exception.class).when(executionService).buy(anyString(),anyDouble(),anyInt());
	}
}
