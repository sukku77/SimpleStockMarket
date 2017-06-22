package com.jpmc.gbce.enums;

public enum TradeType {

    BUY("buy"),
    SELL("sell");

    private String name;

    TradeType(String text) {
        this.name = text;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
