package com.jpmc.gbce.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpmc.gbce.modal.Trade;

/**
 * This class is used to add trade and to fetch trades for a given stock
 * @author Sukumar
 *
 */
public class TradeRepository {

    private Map<String, List<Trade>> trades = new HashMap<>();
    
    /**
     * This method is used to add trade object for a given stock
     * @param stockSymbol
     * @param trade
     */
    public void addTrade(String stockSymbol, Trade trade) {
    	if(!trades.containsKey(stockSymbol)){
    		List<Trade> tradeList = new ArrayList<Trade>();
    		tradeList.add(trade);
    		this.trades.put(stockSymbol, tradeList);
    	}else{
    		this.trades.get(stockSymbol).add(trade);
    	}
    }

    /**
     * This method is used to fetch all trades for a given stock
     * @param stockSymbol
     * @return List<Trade>
     * @throws Exception
     */
    public List<Trade> getTrades(String stockSymbol) throws Exception{
        List<Trade> tradesList = this.trades.get(stockSymbol);
        if (tradesList == null || tradesList.isEmpty()) {
            throw new Exception("Trades not happened for the stock '"+stockSymbol+"'");
        }
        return tradesList;
    }
}
