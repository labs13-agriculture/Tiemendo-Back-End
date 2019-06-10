package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.FarmerContact;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FarmerContactRepository extends PagingAndSortingRepository<FarmerContact, Long> {
}
