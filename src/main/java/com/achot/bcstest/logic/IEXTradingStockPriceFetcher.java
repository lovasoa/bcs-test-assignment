package com.achot.bcstest.logic;

import com.achot.bcstest.model.IEXTradingStockPrice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
class IEXTradingStockPriceFetcher {
    @Value("${iex_api.url}")
    private String IEX_API_URL;

    IEXTradingStockPrice fetch(String symbol) {
        return new RestTemplate().getForObject(IEX_API_URL, IEXTradingStockPrice.class, symbol);
    }
}
