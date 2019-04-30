package com.lovasoa.bcstest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InputStocks {
    private List<Stock> stocks;

    @JsonCreator
    public InputStocks(
            @JsonProperty("stocks") List<Stock> stocks
    ) {
        this.stocks = stocks;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public static class Stock {
        private String symbol;
        private long volume;

        @JsonCreator
        public Stock(
                @JsonProperty("symbol") String symbol,
                @JsonProperty("volume") long volume
        ) {
            this.symbol = symbol;
            this.volume = volume;
        }

        public String getSymbol() {
            return symbol;
        }

        public long getVolume() {
            return volume;
        }
    }
}
