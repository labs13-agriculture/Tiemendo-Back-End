package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Organization;
import com.lambdaschool.tiemendo.model.OrganizationContact;
import com.lambdaschool.tiemendo.model.OrganizationLocation;

import java.util.List;

public interface OrganizationService
{

    List<Organization> findAll();

    List<OrganizationContact> findAllContacts();

    List<OrganizationLocation> findAllLocations();

    Organization findOrganizationById(long id);

    Organization save (Organization organization);

    Organization update(Organization organization, long id);

    void delete(long id);

    void deleteContact(long id);

    void deleteLocation(long id);

}
