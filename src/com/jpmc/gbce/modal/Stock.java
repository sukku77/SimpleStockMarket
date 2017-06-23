package com.jpmc.gbce.modal;

import com.jpmc.gbce.enums.StockType;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(stockSymbol, stock.stockSymbol) &&
                stockType == stock.stockType &&
                Objects.equals(lastDividend, stock.lastDividend) &&
                Objects.equals(fixedDividend, stock.fixedDividend) &&
                Objects.equals(parValue, stock.parValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockSymbol, stockType, lastDividend, fixedDividend, parValue);
    }
}
