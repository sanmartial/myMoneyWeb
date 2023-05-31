package com.globaroman.mymoneyweb.controller;

import com.globaroman.mymoneyweb.controller.model.ResultMoney;
import com.globaroman.mymoneyweb.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
public class MoneyController {
    private final ResultService resultService;

    @Autowired
    public MoneyController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/main")
    public String getMainPage() {
        return "main";
    }

    @PostMapping("/process-data")
    public ModelAndView outputData(@RequestParam("amount-ua1") double am1,
                                   @RequestParam("amount-ua2") double am2,
                                   @RequestParam("amount-ua3") double am3,
                                   @RequestParam("amount-usd1") double us1,
                                   @RequestParam("amount-usd2") double us2,
                                   @RequestParam("exchange-rate") double exR1) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        double total = getSummUA(am1, am2, am3) + getSummUSD(us1, us2, exR1);
        ResultMoney moneyPr = getPreResultMoney();
        double priorTotal = moneyPr.getResult();
        ResultMoney moneyCur = new ResultMoney(total, LocalDate.now());
        resultService.saveResultMoney(moneyCur);
        double diff = priorTotal - total;
        ModelAndView modelAndView = new ModelAndView("outputData");
        modelAndView.addObject("total", decimalFormat.format(total));
        modelAndView.addObject("priorTotal", decimalFormat.format(priorTotal));
        modelAndView.addObject("diff", decimalFormat.format(diff));
        modelAndView.addObject("data", moneyPr.getDataInput());
        return modelAndView;
    }

    private double getSummUSD(double exR1, double ... us) {
        double sum = 0.00;
        for (double amount : us) {
            sum += amount;
        }
        return sum * exR1;}

    private double getSummUA(double...am) {
        double sum = 0.00;
        for (double amount : am) {
            sum += amount;
        }
        return sum;}

    private ResultMoney getPreResultMoney() {
        List<ResultMoney> listsResult = resultService.getAllResult();
        return listsResult.get(listsResult.size() - 1);
    }

}
