package com.achot.bcstest.model;

import java.util.ArrayList;

public class InputStocks {
    private ArrayList<Stock> stocks;

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public static class Stock {
        private String symbol;
        private long volume;

        public String getSymbol() {
            return symbol;
        }

        public long getVolume() {
            return volume;
        }
    }
}
