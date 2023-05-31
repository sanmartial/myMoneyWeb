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
    public ModelAndView outputData(@RequestParam("amount-ua1") int am1,
                                   @RequestParam("amount-ua2") int am2,
                                   @RequestParam("amount-ua3") int am3,
                                   @RequestParam("amount-usd1") int us1,
                                   @RequestParam("amount-usd2") int us2,
                                   @RequestParam("exchange-rate") double exR1) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        int summUa = am1 + am2 + am3;
        double summUsd = (us1 + us2) * exR1;
        double total = summUsd + summUa;
        List<ResultMoney> listsResult = resultService.getAllResult();
        ResultMoney moneyPr = listsResult.get(listsResult.size() - 1);
        double priorTotal = moneyPr.getResult();
        ResultMoney money = new ResultMoney(total, LocalDate.now());
        resultService.saveResultMoney(money);
        double diff = priorTotal - total;
        ModelAndView modelAndView = new ModelAndView("outputData");
        modelAndView.addObject("total", decimalFormat.format(total));
        modelAndView.addObject("priorTotal", decimalFormat.format(priorTotal));
        modelAndView.addObject("diff", decimalFormat.format(diff));
        modelAndView.addObject("data", moneyPr.getDataInput());
        return modelAndView;
    }

}
