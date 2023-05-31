package com.globaroman.mymoneyweb.controller.model.repository;

import com.globaroman.mymoneyweb.controller.model.ResultMoney;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<ResultMoney, Long> {
}
