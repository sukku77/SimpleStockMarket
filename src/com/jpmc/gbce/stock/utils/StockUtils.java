package com.jpmc.gbce.stock.utils;

import com.jpmc.gbce.enums.StockType;
import com.jpmc.gbce.enums.TradeType;
import com.jpmc.gbce.modal.Stock;
import com.jpmc.gbce.modal.Trade;

/**
 * This is the utility class used to reuse the methods
 * @author Sukumar
 *
 */
public class StockUtils {

	private static final long FIFTEEN_MINUTES = 15 * 60 * 1000;

	/**
	 * This method is used to calculate Dividend
	 * @param stock
	 * @return Double
	 */
	public static Double getDividend(Stock stock) {
		StockType stockType = null;
		if (null != stock) {
			stockType = stock.getStockType();
			return stockType.equals(StockType.COMMON) ? stock.getLastDividend()
					: (stock.getFixedDividend() * stock.getParValue());
		}
		return null;
	}

	/**
	 * This method is used to identify whether the particular trade is happened in last 15min or not
	 * @param trade
	 * @return boolean
	 */
	public static boolean isTradeHappenedInLast15min(Trade trade) {
		long fifteenMinAgo = System.currentTimeMillis() - FIFTEEN_MINUTES;
		return trade.getTimestamp().getTime() >= fifteenMinAgo;
	}

	public static boolean isValidTradeType(String tradeType) {
		try {
			if(TradeType.valueOf(tradeType.toUpperCase()).equals(TradeType.BUY) || 
					TradeType.valueOf(tradeType.toUpperCase()).equals(TradeType.SELL)) {
			 return true;	
			}else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	
}
