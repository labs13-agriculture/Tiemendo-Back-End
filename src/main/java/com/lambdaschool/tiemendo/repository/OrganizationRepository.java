package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long>
{
}
