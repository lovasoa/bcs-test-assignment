package com.achot.bcstest.logic;

import com.achot.bcstest.model.IEXTradingStockPrice;
import com.achot.bcstest.model.InputStocks;
import com.achot.bcstest.model.PortfolioEval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortfolioEvaluatorTest {

    @MockBean
    private IEXTradingStockPriceFetcher iexTradingStockPriceFetcher;

    @Autowired
    private PortfolioEvaluator portfolioEvaluator;

    @Test
    public void eval() {
        // Fake API responses
        Mockito.when(iexTradingStockPriceFetcher.fetch("AAPL"))
                .thenReturn(new IEXTradingStockPrice(3, "tech"));
        Mockito.when(iexTradingStockPriceFetcher.fetch("YADX"))
                .thenReturn(new IEXTradingStockPrice(.1, "tech"));
        Mockito.when(iexTradingStockPriceFetcher.fetch("XXX"))
                .thenReturn(new IEXTradingStockPrice(1, "agro"));


        // Fake stock volume input
        List<InputStocks.Stock> stocks = Arrays.asList(
                new InputStocks.Stock("AAPL", 3),
                new InputStocks.Stock("YADX", 10),
                new InputStocks.Stock("XXX", 90)
        );
        InputStocks inputStocks = new InputStocks(stocks);

        PortfolioEval portfolioEval = portfolioEvaluator.eval(inputStocks);

        // Asserts
        assertEquals(100, portfolioEval.getValue(), .001);
        List<PortfolioEval.Allocation> expectedAllocations = Arrays.asList(
                new PortfolioEval.Allocation("tech", 10, .1),
                new PortfolioEval.Allocation("agro", 90, .9)
        );
        assertEquals(expectedAllocations, portfolioEval.getAllocations());
    }
}