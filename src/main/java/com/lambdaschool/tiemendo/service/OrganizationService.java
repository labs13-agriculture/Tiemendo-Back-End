package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Organization;

import java.util.List;

public interface OrganizationService
{

    List<Organization> findAll();


    Organization findOrganizationById(long id);

    Organization save (Organization organization);

    Organization update(Organization organization, long id);

}
