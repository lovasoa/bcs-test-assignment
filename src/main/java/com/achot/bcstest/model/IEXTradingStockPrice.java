package com.achot.bcstest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IEXTradingStockPrice {

    @JsonCreator
    public IEXTradingStockPrice(
            @JsonProperty("latestPrice") double latestPrice,
            @JsonProperty("sector") String sector
    ) {
        this.latestPrice = latestPrice;
        this.sector = sector;
    }

    private double latestPrice;
    private String sector;

    public double getLatestPrice() {
        return latestPrice;
    }


    public String getSector() {
        return sector;
    }
}
