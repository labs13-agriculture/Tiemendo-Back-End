package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Organization;
import com.lambdaschool.tiemendo.model.OrganizationBranch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrganizationService
{

    List<Organization> findAll(Pageable pageable, boolean lead);

    List<OrganizationBranch> findAllBranches();
    
    List<OrganizationBranch> findBranchesByOrganization(long id);

    List<Organization> searchOrganizations(Pageable pageable, String name, String location, boolean lead);

    Organization findOrganizationById(long id);

    Organization save (Organization organization);

    Organization update(Organization organization, long id);

    void delete(long id);

    List<OrganizationBranch> deleteBranch(long id);
    
    List<OrganizationBranch> saveBranch(long id, OrganizationBranch newBranch);
    
    List<OrganizationBranch> updateBranch(long id, OrganizationBranch branch);
}
