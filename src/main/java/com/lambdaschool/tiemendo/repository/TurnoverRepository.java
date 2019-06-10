package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Retailer;
import com.lambdaschool.tiemendo.model.Turnover;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TurnoverRepository extends PagingAndSortingRepository<Turnover, Long> {

    List<Turnover> findAllByRetailer(Pageable p, Retailer r);
}
