package com.lovasoa.bcstest.model;

import java.util.List;
import java.util.Objects;

public class PortfolioEval {
    private double value;
    private List<Allocation> allocations;

    public PortfolioEval(double value, List<Allocation> allocations) {
        this.value = value;
        this.allocations = allocations;
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

        public Allocation(String sector, double assetValue, double proportion) {
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

        @Override
        public String toString() {
            return "Allocation{" +
                    "sector='" + sector + '\'' +
                    ", assetValue=" + assetValue +
                    ", proportion=" + proportion +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Allocation that = (Allocation) o;
            return Double.compare(that.assetValue, assetValue) == 0 &&
                    Double.compare(that.proportion, proportion) == 0 &&
                    Objects.equals(sector, that.sector);
        }

        @Override
        public int hashCode() {
            return Objects.hash(sector, assetValue, proportion);
        }
    }
}
