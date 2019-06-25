package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Organization;
import com.lambdaschool.tiemendo.model.OrganizationBranch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrganizationService {

    Page<Organization> findAll(Pageable pageable, boolean lead);

    Page<Organization> searchOrganizations(Pageable pageable, String name, String location, boolean lead);

    Organization findOrganizationById(long id);

    Organization save(Organization organization);

    Organization update(Organization organization, long id);

    void delete(long id);

    /*
    *
    *  This all deals with branches
    *
    * */

    Page<OrganizationBranch> findAllBranches(Pageable pageable);

    Page<OrganizationBranch> findBranchesByOrganization(Pageable pageable, long id);

    Page<OrganizationBranch> deleteBranch(Pageable pageable, long id);

    Page<OrganizationBranch> saveBranch(Pageable pageable, long id, OrganizationBranch newBranch);

    Page<OrganizationBranch> updateBranch(Pageable pageable, long id, OrganizationBranch branch);
}
