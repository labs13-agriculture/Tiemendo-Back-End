package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Farmer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FarmerRepository extends PagingAndSortingRepository<Farmer, Long> {
}
