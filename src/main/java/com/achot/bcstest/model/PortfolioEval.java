package com.achot.bcstest.model;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class PortfolioEval {
    private double value;
    private List<Allocation> allocations;

    public PortfolioEval(InputStocks inputStocks) {
        // Create a map from stock symbols to the associated latest price
        List<StockWithPrice> withPrices = inputStocks.getStocks().stream().map(StockWithPrice::new).collect(toList());
        this.value = withPrices.stream().mapToDouble(StockWithPrice::totalValue).sum();
        this.allocations = withPrices.stream()
                .collect(Collectors.groupingBy(StockWithPrice::getSector))
                .values()
                .stream()
                .map(stocks -> Allocation.fromPrices(stocks, this.value))
                .collect(toList());
    }

    public double getValue() {
        return value;
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    public static class Allocation {
        private final String sector;
        private final double assetValue;
        private final double proportion;

        Allocation(String sector, double assetValue, double proportion) {
            this.sector = sector;
            this.assetValue = assetValue;
            this.proportion = proportion;
        }

        public Allocation(StockWithPrice stockWithPrice, double totalValue) {
            this.sector = stockWithPrice.getPrice().getSector();
            this.assetValue = stockWithPrice.totalValue();
            this.proportion = this.assetValue / totalValue;
        }

        public String getSector() {
            return sector;
        }

        public double getAssetValue() {
            return assetValue;
        }

        public double getProportion() {
            return proportion;
        }

        static Allocation fromPrices(List<StockWithPrice> stocks, double totalValue) {
            String sector = stocks.get(0).getSector();
            double assetValue = stocks.stream().mapToDouble(StockWithPrice::totalValue).sum();
            double proportion = assetValue / totalValue;
            return new Allocation(sector, proportion, assetValue);
        }
    }
}
