package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Organization;
import com.lambdaschool.tiemendo.model.OrganizationBranch;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrganizationBranchRepository extends PagingAndSortingRepository<OrganizationBranch, Long>
{
    List<OrganizationBranch> findAllByOrganization(Organization org);
}
