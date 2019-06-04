package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Yield;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface YieldRepository extends PagingAndSortingRepository<Yield, Long> {
}
