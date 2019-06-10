package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Turnover;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TurnoverRepository extends PagingAndSortingRepository<Turnover, Long> {
}
