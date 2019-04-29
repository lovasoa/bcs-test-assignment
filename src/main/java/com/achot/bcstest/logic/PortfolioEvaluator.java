package com.achot.bcstest.logic;

import com.achot.bcstest.model.IEXTradingStockPrice;
import com.achot.bcstest.model.InputStocks;
import com.achot.bcstest.model.PortfolioEval;
import com.achot.bcstest.model.StockWithPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class PortfolioEvaluator {

    @Autowired
    private
    IEXTradingStockPriceFetcher iexTradingStockPriceFetcher;

    private static PortfolioEval.Allocation allocationFromPrices(List<StockWithPrice> stocks, double totalValue) {
        String sector = stocks.get(0).getSector();
        double assetValue = stocks.stream().mapToDouble(StockWithPrice::totalValue).sum();
        double proportion = assetValue / totalValue;
        return new PortfolioEval.Allocation(sector, assetValue, proportion);
    }

    public PortfolioEval eval(InputStocks inputStocks) {
        // Create a map from stock symbols to the associated latest price
        List<StockWithPrice> withPrices = inputStocks.getStocks().stream().map(this::withPrice).collect(toList());
        double value = withPrices.stream().mapToDouble(StockWithPrice::totalValue).sum();
        List<PortfolioEval.Allocation> allocations = withPrices.stream()
                .collect(Collectors.groupingBy(StockWithPrice::getSector))
                .values()
                .stream()
                .map(stocks -> allocationFromPrices(stocks, value))
                .collect(toList());
        return new PortfolioEval(value, allocations);
    }

    private StockWithPrice withPrice(InputStocks.Stock stock) {
        IEXTradingStockPrice price = iexTradingStockPriceFetcher.fetch(stock.getSymbol());
        return new StockWithPrice(stock, price);
    }
}
