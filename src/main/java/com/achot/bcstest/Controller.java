package com.achot.bcstest;


import com.achot.bcstest.logic.PortfolioEvaluator;
import com.achot.bcstest.model.InputStocks;
import com.achot.bcstest.model.PortfolioEval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class Controller {
    @Autowired
    private PortfolioEvaluator portfolioEval;

    @RequestMapping(value = "/", method = POST)
    public PortfolioEval portfolio(@RequestBody InputStocks inputStocks) {
        return portfolioEval.eval(inputStocks);
    }
}