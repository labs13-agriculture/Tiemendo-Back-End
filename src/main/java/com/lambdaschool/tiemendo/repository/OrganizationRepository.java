package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Organization;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<Organization, Long>
{
}
