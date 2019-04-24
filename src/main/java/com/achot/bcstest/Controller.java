package com.achot.bcstest;


import com.achot.bcstest.model.InputStocks;
import com.achot.bcstest.model.PortfolioEval;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class Controller {

    @RequestMapping(value = "/", method = POST)
    public PortfolioEval portfolio(@RequestBody InputStocks inputStocks) {
        return new PortfolioEval(inputStocks);
    }
}