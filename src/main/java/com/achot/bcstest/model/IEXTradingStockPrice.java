package com.achot.bcstest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.client.RestTemplate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IEXTradingStockPrice {
    public static final String IEX_API_URL = "https://api.iextrading.com/1.0/stock/{symbol}/quote";

    private double latestPrice;
    private String sector;

    public IEXTradingStockPrice() {
    }

    public static IEXTradingStockPrice fetch(String symbol) {
        return new RestTemplate().getForObject(IEX_API_URL, IEXTradingStockPrice.class, symbol);
    }

    public double getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(double latestPrice) {
        this.latestPrice = latestPrice;
    }


    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
