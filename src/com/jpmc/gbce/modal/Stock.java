package com.jpmc.gbce.modal;

import com.jpmc.gbce.enums.StockType;

/**
 * This class represents Stock object which holds stockSymbol, stockType,lastDividend,fixedDividend and parValue
 * @author Sukumar
 *
 */
public class Stock {

    private String stockSymbol;

    private StockType stockType;

    private Double lastDividend;

    private Double fixedDividend;

    private Double parValue;

    public Stock() {

    }
    
    public Stock(String stockSymbol, StockType stockType, Double lastDividend, Double fixedDividend, Double parValue) {
		super();
		this.stockSymbol = stockSymbol;
		this.stockType = stockType;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}


	public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public StockType getStockType() {
        return stockType;
    }

    public void setStockType(StockType stockType) {
        this.stockType = stockType;
    }

    public Double getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(Double lastDividend) {
        this.lastDividend = lastDividend;
    }

    public Double getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(Double fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    public Double getParValue() {
        return parValue;
    }

    public void setParValue(Double parValue) {
        this.parValue = parValue;
    }

	@Override
	public String toString() {
		return "Stock [stockSymbol=" + stockSymbol + ", stockType=" + stockType + ", lastDividend=" + lastDividend
				+ ", fixedDividend=" + fixedDividend + ", parValue=" + parValue + "]";
	}
    
    
}
