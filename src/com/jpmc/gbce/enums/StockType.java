package com.jpmc.gbce.enums;

public enum StockType {

    COMMON("Common"),
    PREFERRED("Preferred");

    private String name;

    StockType(String text) {
        this.name = text;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

