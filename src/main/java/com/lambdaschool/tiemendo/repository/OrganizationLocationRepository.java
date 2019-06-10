package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.OrganizationLocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrganizationLocationRepository extends PagingAndSortingRepository<OrganizationLocation, Long>
{
}
