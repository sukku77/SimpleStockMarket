package com.jpmc.gbce.stockservice;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.jpmc.gbce.modal.Stock;
import com.jpmc.gbce.modal.Trade;
import com.jpmc.gbce.repository.StockRepository;
import com.jpmc.gbce.repository.TradeRepository;
import com.jpmc.gbce.stock.utils.StockUtils;

/**
 * This class provides all operations for stock trade
 * 
 * @author Sukumar
 *
 */
public class StockService {

	private StockRepository stockEntity;

	private TradeRepository tradeEntity;

	public StockService(StockRepository stockEntity, TradeRepository tradeEntity) {
		this.stockEntity = stockEntity;
		this.tradeEntity = tradeEntity;
	}

	public void addStock(Stock stock) {
		this.stockEntity.addStock(stock);
	}

	public boolean isValidStock(String stockSymbol) {
		return this.stockEntity.isValidStock(stockSymbol);
	}

	public void addTrade(String stockSymbol, Trade trade) {
		this.tradeEntity.addTrade(stockSymbol, trade);
	}

	/**
	 * This method is used to calculate dividend yield for a given stock and
	 * price
	 * 
	 * @param stockSymbol
	 * @param price
	 * @return Double
	 * @throws Exception
	 */
	public Double dividendYield(String stockSymbol, double price) throws Exception {
		Double dividend = 0.0;
		Stock stock = this.stockEntity.getStock(stockSymbol);
		if (null != stock) {
			dividend = StockUtils.getDividend(stock) / price;
		}
		return dividend;
	}

	/**
	 * This method is used to calculate P/E Ratio for a given stock and price
	 * 
	 * @param stockSymbol
	 * @param price
	 * @return Double
	 * @throws Exception
	 */
	public Double priceEarningRatio(String stockSymbol, double price) throws Exception {
		Double dividend = 0.0;
		Stock stock = this.stockEntity.getStock(stockSymbol);
		if (null != stock) {
			dividend = StockUtils.getDividend(stock);
		}
		return dividend != 0 ? price / dividend : dividend; 
	}

	/**
	 * This method is used to calculate volume weighted average price in the past 15 minutes using
	 * stocksymbol
	 * 
	 * @param stockSymbol
	 * @return Double
	 * @throws Exception
	 */
	public BigDecimal volumeWeightedAveragePrice(String stockSymbol) throws Exception {
		if (this.isValidStock(stockSymbol)) {
			//Get all trades for a given stock
			List<Trade> tradeList = this.tradeEntity.getTrades(stockSymbol);
			if (tradeList == null || tradeList.isEmpty()) {
				throw new Exception("Trading not happened for this stock");
			}
			
			//Get the list of trades which have happened in the past 15 minutes
			List<Trade> tradesHappenedIn15Min = tradeList.stream()
					.filter(trade -> StockUtils.isTradeHappenedInLast15min(trade)).collect(Collectors.toList());
			if (tradesHappenedIn15Min == null || tradesHappenedIn15Min.isEmpty()) {
				throw new Exception("Trading not happened in the last 15 Minutes for this stock");
			}
			
			Integer totalTradeQuantity = 0;
			BigDecimal totalTradePrice = new BigDecimal(0.0);
			
			for (Trade trade : tradesHappenedIn15Min) {
				Integer quantity = trade.getQuantity();
				totalTradeQuantity = totalTradeQuantity + quantity;
				BigDecimal x = BigDecimal.valueOf((trade.getQuantity() * trade.getPrice()));
				totalTradePrice = x.add(totalTradePrice);
			}
			//Formula to calculate volume weighted average price
			return totalTradePrice.divide(BigDecimal.valueOf(totalTradeQuantity));
		}
		return null;
	}

	/**
	 * This method is used to calculate GCBE All share Index for all stocks
	 * 
	 * @return Double
	 */
	public Double gbceAllShareIndex() {
		Double gbceAllShareIndex = 0.0;
		int shareIndex = 0;
		Map<String, Stock> stockMap = this.stockEntity.getAllStocks();
		Set<String> stockSymbols = stockMap.keySet();
		for (String stockSymbol : stockSymbols) {
			Double price = getPrice(stockSymbol);
			if (price != null) {
				gbceAllShareIndex += price;
				shareIndex += 1;
			}
		}
		return Math.pow(gbceAllShareIndex, 1.0 / shareIndex);
	}

	/**
	 * This method is used to get latest trade price for a given stock
	 * 
	 * @param stockSymbol
	 * @return Double
	 */
	 private Double getPrice(String stockSymbol) {
		List<Trade> tradeList = null;
		try {
			    tradeList = this.tradeEntity.getTrades(stockSymbol);
				Trade trade = tradeList.get(tradeList.size() - 1);
				return trade.getPrice();			
		} catch (Exception e) {
		}
		return null;
	}
}
