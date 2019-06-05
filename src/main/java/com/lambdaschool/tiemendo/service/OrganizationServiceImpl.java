package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.model.Organization;
import com.lambdaschool.tiemendo.model.OrganizationContact;
import com.lambdaschool.tiemendo.model.OrganizationLocation;
import com.lambdaschool.tiemendo.repository.OrganizationContactRepository;
import com.lambdaschool.tiemendo.repository.OrganizationLocationRepository;
import com.lambdaschool.tiemendo.repository.OrganizationRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "organizationService")
public class OrganizationServiceImpl implements OrganizationService
{

    @Autowired
    private OrganizationRepository organizationRepos;

    @Autowired
    private OrganizationLocationRepository organizationLocationRepos;

    @Autowired
    private OrganizationContactRepository organizationContactRepos;


    @Override
    public List<Organization> findAll()
    {
        List<Organization> list = new ArrayList<>();
        organizationRepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Override
    public Organization findOrganizationById(long id) throws EntityNotFoundException
    {
        return organizationRepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Transactional
    @Override
    public Organization save(Organization organization)
    {
        Organization newOrganization = new Organization();

        newOrganization.setBeneficiaries(organization.getBeneficiaries());
        newOrganization.setHeadquarters(organization.getHeadquarters());

        // ArrayList to hold new contacts
        List<OrganizationContact> contactlist = new ArrayList<>();
        organization.getOrganizationcontacts().iterator().forEachRemaining(c ->
        {
        c.setOrganization(newOrganization);
        contactlist.add(c);
        });

        List<OrganizationLocation> locationlist = new ArrayList<>();
        organization.getOrganizationlocations().iterator().forEachRemaining(l ->
        {
            l.setOrganization(newOrganization);
            locationlist.add(l);
        });

        newOrganization.setOrganizationcontacts(organization.getOrganizationcontacts());
        newOrganization.setOrganizationlocations(organization.getOrganizationlocations());


        Organization organizationSaved = organizationRepos.save(newOrganization);
        organizationContactRepos.saveAll(contactlist);
        organizationLocationRepos.saveAll(locationlist);

        return organizationSaved;
    }

    @Override
    public Organization update(Organization organization, long id)
    {
        return null;
    }


    @Transactional
    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (organizationRepos.findById(id).isPresent())
        {
            organizationRepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public void deleteContact(long id) throws EntityNotFoundException
    {
        if(organizationContactRepos.findById(id).isPresent())
        {
            organizationContactRepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public void deleteLocation(long id) throws EntityNotFoundException
    {
        if(organizationLocationRepos.findById(id).isPresent())
        {
            organizationLocationRepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }
}
