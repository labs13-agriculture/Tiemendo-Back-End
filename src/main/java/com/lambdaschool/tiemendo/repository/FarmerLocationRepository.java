package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.FarmerLocation;
import org.springframework.data.repository.CrudRepository;

public interface FarmerLocationRepository extends CrudRepository<FarmerLocation, Long> {
}
