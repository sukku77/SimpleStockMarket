package com.jpmc.gbce.modal;

import java.util.Date;

import com.jpmc.gbce.enums.TradeType;

/**
 * This class is Trade object which holds tradeType, price, quantity and timestamp
 * @author Sukumar
 *
 */
public class Trade {

    private String stockSymbol;

    private TradeType tradeType;

    private Double price;

    private Integer quantity;

    private Date timestamp;

    public Trade() {

    }    
    public Trade(TradeType tradeType, Double price, Integer quantity) {
		super();
		this.tradeType = tradeType;
		this.price = price;
		this.quantity = quantity;
		this.timestamp = new Date();
	}


	public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
	@Override
	public String toString() {
		return "Trade [tradeType=" + tradeType + ", price=" + price + ", quantity=" + quantity + ", timestamp="
				+ timestamp + "]";
	}
    
    
}
