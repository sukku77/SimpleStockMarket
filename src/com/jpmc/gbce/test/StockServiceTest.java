package com.jpmc.gbce.test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.jpmc.gbce.enums.StockType;
import com.jpmc.gbce.enums.TradeType;
import com.jpmc.gbce.modal.Stock;
import com.jpmc.gbce.modal.Trade;
import com.jpmc.gbce.repository.StockRepository;
import com.jpmc.gbce.repository.TradeRepository;
import com.jpmc.gbce.stockservice.StockService;


/**
 * This class is junit test cases
 * @author Sukumar
 *
 */
public class StockServiceTest {    
	private final String STOCK_SYMBOL_POP = "POP";
	private final String STOCK_SYMBOL_ALE = "ALE";
    
	@Mock
	private StockRepository stockEntity;

	@Mock
	private TradeRepository tradeEntity;

	StockService service;
	
	
	 @Before
	 public void setUp(){		
		 MockitoAnnotations.initMocks(this);
		 this.service = new StockService(this.stockEntity, this.tradeEntity);
	 }
		 
	@Test
	public void testgbceAllShareIndexForSingleTrade() throws Exception {
				
		Map<String, Stock> allStocks = TestUtils.getAllStocks();		
		when(this.stockEntity.getAllStocks()).thenReturn(allStocks);		
		when(this.stockEntity.isValidStock(STOCK_SYMBOL_POP)).thenReturn(true);
		when(this.tradeEntity.getTrades(STOCK_SYMBOL_POP))
				.thenReturn(Collections.singletonList(new Trade(TradeType.BUY, 30.0, 3)));				
		Double gbceAllShareIndex = service.gbceAllShareIndex();
		assertEquals(30.0, gbceAllShareIndex);
		verify(this.tradeEntity).getTrades(STOCK_SYMBOL_POP);		
	}

	@Test
	public void testgbceAllShareIndexToVerifyLatestPriceIfMultipleTradeForSameStock() throws Exception {	
				
		Map<String, Stock> allStocks = TestUtils.getAllStocks();		
		when(this.stockEntity.getAllStocks()).thenReturn(allStocks);		
		when(this.stockEntity.isValidStock(STOCK_SYMBOL_POP)).thenReturn(true);
		when(this.tradeEntity.getTrades(STOCK_SYMBOL_POP))
				.thenReturn(Arrays.asList(new Trade(TradeType.SELL, 30.0, 3),new Trade(TradeType.BUY, 20.0, 3)));
		Double gbceAllShareIndex = service.gbceAllShareIndex();
		assertEquals(20.0, gbceAllShareIndex);
		verify(this.tradeEntity).getTrades(STOCK_SYMBOL_POP);
	}

	@Test
	public void testgbceAllShareIndexIfMultipleTradeForDifferentStock() throws Exception {					
		Map<String, Stock> allStocks = TestUtils.getAllStocks();		
		when(this.stockEntity.getAllStocks()).thenReturn(allStocks);		
		when(this.tradeEntity.getTrades(STOCK_SYMBOL_POP))
				.thenReturn(Arrays.asList(new Trade(TradeType.BUY, 30.0, 3)));		
		when(this.tradeEntity.getTrades(STOCK_SYMBOL_ALE))
				.thenReturn(Arrays.asList(new Trade(TradeType.SELL, 20.0, 3)));
		Double gbceAllShareIndex = service.gbceAllShareIndex();
		assertEquals(7.0710678118654755, gbceAllShareIndex);		
		verify(this.tradeEntity,Mockito.times(1)).getTrades(STOCK_SYMBOL_ALE);
		verify(this.tradeEntity,Mockito.times(1)).getTrades(STOCK_SYMBOL_POP);
	}

	@Test
	public void testDividendYield() throws Exception {			
		when(this.stockEntity.getStock(STOCK_SYMBOL_POP)).thenReturn(new Stock(STOCK_SYMBOL_POP, StockType.COMMON, 8.0, 0.0, 100.0));
		Double dividend = service.dividendYield(STOCK_SYMBOL_POP,30.0);
		assertEquals(0.26666666666666666, dividend);
		verify(this.stockEntity).getStock(STOCK_SYMBOL_POP);
	}
	
	@Test
	public void testDividendYieldIfInvalidStockSymbol() throws Exception {			
		when(this.stockEntity.getStock("AAA")).thenReturn(null);
		Double dividend = service.dividendYield("AAA",30.0);
		assertEquals(0.0, dividend);
		verify(this.stockEntity).getStock("AAA");
	}
	
	@Test
	public void testPriceEarningRatio() throws Exception {			
		when(this.stockEntity.getStock(STOCK_SYMBOL_POP)).thenReturn(new Stock(STOCK_SYMBOL_POP, StockType.COMMON, 8.0, 0.0, 100.0));
		Double dividend = service.priceEarningRatio(STOCK_SYMBOL_POP,30.0);
		assertEquals(3.75, dividend);
		verify(this.stockEntity).getStock(STOCK_SYMBOL_POP);
	}
	
	@Test
	public void testPriceEarningRatioIfInvalidStockSymbol() throws Exception {		
		when(this.stockEntity.getStock("AAA")).thenReturn(null);
		Double dividend = service.dividendYield("AAA",30.0);
		assertEquals(0.0, dividend);
		verify(this.stockEntity).getStock("AAA");
	}
	
	@Test
	public void testvolumeWeightedAveragePrice() throws Exception{
		List<Trade> trades = TestUtils.getTrades(STOCK_SYMBOL_POP);
		when(this.stockEntity.isValidStock(STOCK_SYMBOL_POP)).thenReturn(true);
		when(this.tradeEntity.getTrades(STOCK_SYMBOL_POP)).thenReturn(trades);
		BigDecimal volumeWeightedAvgPrice = service.volumeWeightedAveragePrice(STOCK_SYMBOL_POP);
	    assertEquals(BigDecimal.valueOf(24.6), volumeWeightedAvgPrice);
	    verify(this.stockEntity).isValidStock(STOCK_SYMBOL_POP);
	}
	
	@Test
	public void testvolumeWeightedAveragePriceIfNotValidStock() throws Exception{
		
		List<Trade> trades = TestUtils.getTrades(STOCK_SYMBOL_POP);
		when(this.stockEntity.isValidStock(STOCK_SYMBOL_POP)).thenReturn(false);
		when(this.tradeEntity.getTrades(STOCK_SYMBOL_POP)).thenReturn(trades);
		BigDecimal volumeWeightedAvgPrice = service.volumeWeightedAveragePrice(STOCK_SYMBOL_POP);
	    assertEquals(null, volumeWeightedAvgPrice);
	    verify(this.stockEntity).isValidStock(STOCK_SYMBOL_POP);
	}
	
	
}
