package com.globaroman.mymoneyweb.service;

import com.globaroman.mymoneyweb.controller.model.ResultMoney;
import com.globaroman.mymoneyweb.controller.model.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    private final ResultRepository repository;

    @Autowired
    public ResultService(ResultRepository repository) {
        this.repository = repository;
    }

    public void saveResultMoney(ResultMoney money) {
        repository.save(money);
    }

    public List<ResultMoney> getAllResult() {
        return repository.findAll();
    }
}
