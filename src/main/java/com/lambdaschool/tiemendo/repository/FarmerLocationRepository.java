package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.FarmerLocation;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FarmerLocationRepository extends PagingAndSortingRepository<FarmerLocation, Long> {
}
