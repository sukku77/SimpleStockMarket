package com.jpmc.gbce.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpmc.gbce.enums.StockType;
import com.jpmc.gbce.enums.TradeType;
import com.jpmc.gbce.modal.Stock;
import com.jpmc.gbce.modal.Trade;
/**
 * This class is to reuse methods for test cases
 * @author Sukumar
 *
 */
public class TestUtils {
	public static Map<String, Stock> getAllStocks() {
		Map<String,Stock> stocks = new HashMap<String,Stock>();
		stocks.put("TEA", new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
		stocks.put("POP", new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0));
		stocks.put("ALE", new Stock("ALE", StockType.COMMON, 23.0, 0.0, 60.0));
		stocks.put("GIN", new Stock("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0));
		stocks.put("JOE", new Stock("JOE", StockType.COMMON, 13.0, 0.0, 250.0));
		return stocks;		
	}
	
	public static List<Trade> getTrades(String stockSymbol) {
		List<Trade> trades = new ArrayList<Trade>();
		trades.add(new Trade(TradeType.BUY,20.0,3));
		trades.add(new Trade(TradeType.SELL,30.0,2));
		trades.add(new Trade(TradeType.SELL,25.2,5));
		return trades;		
	}
}
