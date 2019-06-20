package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Organization;
import com.lambdaschool.tiemendo.model.OrganizationBranch;

import java.util.List;

public interface OrganizationService
{

    List<Organization> findAll();

    List<OrganizationBranch> findAllBranches();
    
    List<OrganizationBranch> findBranchesByOrganization(long id);

    List<Organization> searchOrganizations(String name, String location, boolean lead);

    Organization findOrganizationById(long id);

    Organization save (Organization organization);

    Organization update(Organization organization, long id);

    void delete(long id);

    void deleteBranch(long id);
}
