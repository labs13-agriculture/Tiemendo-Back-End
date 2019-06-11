package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.OrganizationContact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrganizationContactRepository extends PagingAndSortingRepository<OrganizationContact, Long>
{
}
