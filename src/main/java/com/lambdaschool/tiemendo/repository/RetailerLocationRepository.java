package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.RetailerLocation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RetailerLocationRepository extends PagingAndSortingRepository<RetailerLocation, Long>
{

}
