package com.jpmc.gbce.repository;


import java.util.HashMap;
import java.util.Map;

import com.jpmc.gbce.enums.StockType;
import com.jpmc.gbce.modal.Stock;

/**
 * This class is used to add stock and fetch stocks for a given stock symbol
 * @author Sukumar
 *
 */
public class StockRepository {
	
	private Map<String, Stock> stocks = new HashMap<>();
	
	public StockRepository() {
		super();		
		this.stocks.put("TEA", new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
		this.stocks.put("POP", new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0));
		this.stocks.put("ALE", new Stock("ALE", StockType.COMMON, 23.0, 0.0, 60.0));
		this.stocks.put("GIN", new Stock("GIN", StockType.PREFERRED, 8.0, 0.02, 100.0));
		this.stocks.put("JOE", new Stock("JOE", StockType.COMMON, 13.0, 0.0, 250.0));		
	}
	
	 public boolean	isValidStock(String stockSymbol){
	    return this.stocks.containsKey(stockSymbol);    	
	  }
	 
    public void addStock(Stock stock) {    	
        this.stocks.put(stock.getStockSymbol(), stock);
    }

    public Map<String, Stock> getAllStocks() {
        return this.stocks;
    }

    public Stock getStock(String stockSymbol) throws Exception{
    	if(isValidStock(stockSymbol)){
	        Stock stock = this.stocks.get(stockSymbol);
	        if (stock == null) {
	            throw new Exception("Stock not found for the symbol '"+stockSymbol+"'");
	        }
	        return stock;
    	}
		return null;
    }
}
