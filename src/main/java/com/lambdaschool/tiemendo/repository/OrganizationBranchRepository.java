package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Organization;
import com.lambdaschool.tiemendo.model.OrganizationBranch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrganizationBranchRepository extends PagingAndSortingRepository<OrganizationBranch, Long>
{
    List<OrganizationBranch> findAllByOrganization(Organization org);
    
    //Have to do a custom query here... findByBranch_Id causes issues, underscore makes it look for branch instead of branch_id
    @Query(value = "SELECT * FROM organizationcontacts WHERE branch_id=:id", nativeQuery = true)
    OrganizationBranch getOrganizationBranchBy(long id);
}
