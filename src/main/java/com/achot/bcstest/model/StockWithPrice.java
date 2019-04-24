package com.achot.bcstest.model;

public class StockWithPrice {
    private InputStocks.Stock inputStock;
    private IEXTradingStockPrice price;

    public StockWithPrice(InputStocks.Stock inputStock) {
        this.inputStock = inputStock;
        this.price = IEXTradingStockPrice.fetch(inputStock.getSymbol());
    }

    /**
     * @return the product of the price by the volume of the stock
     */
    public double totalValue() {
        return this.getInputStock().getVolume() * this.getPrice().getLatestPrice();
    }

    public InputStocks.Stock getInputStock() {
        return inputStock;
    }

    public IEXTradingStockPrice getPrice() {
        return price;
    }

    public String getSector() {
        return this.getPrice().getSector();
    }
}
