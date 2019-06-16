package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
import com.lambdaschool.tiemendo.model.Organization;
import com.lambdaschool.tiemendo.model.OrganizationBranch;
import com.lambdaschool.tiemendo.repository.OrganizationBranchRepository;
import com.lambdaschool.tiemendo.repository.OrganizationRepository;
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
    private OrganizationBranchRepository organizationContactRepos;


    @Override
    public List<Organization> findAll()
    {
        List<Organization> list = new ArrayList<>();
        organizationRepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public List<OrganizationBranch> findAllBranches()
    {
        List<OrganizationBranch> list = new ArrayList<>();
        organizationContactRepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public List<Organization> searchOrganizations(String name, String location, boolean lead) throws ResourceNotFoundException
    {
        String wildcardName = "%" + name + "%";
        String wildcardLocation = "%" + location + "%";

        List<Organization> searchResults = organizationRepos.searchOrganizations(wildcardName, wildcardLocation, lead);
        if(searchResults.size() == 0)
        {
            throw new ResourceNotFoundException("No Organizations found");
        }

        //Query still returns duplicates, for now will filter here, modifying query in future would propbably be more performant
        List<Long> existing = new ArrayList<>();
        List<Organization> filteredResults = new ArrayList<>();

        for(Organization o : searchResults)
        {
            //If id hasn't been added to existing list
            if(!existing.contains(o.getId()))
            {
                existing.add(o.getId());
                filteredResults.add(o);
            }
        }

        return filteredResults;
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
        return organizationRepos.save(organization);
    }

    @Override
    public Organization update(Organization org, long id)
    {
        var current = findOrganizationById(id);

        if (org.getName() != null) {
            current.setName(org.getName());
        }
        if (org.isLead() != current.isLead()) {
            current.setLead(org.isLead());
        }
        if (org.getBeneficiaries() != current.getBeneficiaries()) {
            current.setBeneficiaries(org.getBeneficiaries());
        }
        if (org.getHeadquarters() != null) {
            current.setHeadquarters(org.getHeadquarters());
        }

        return organizationRepos.save(current);
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
    public void deleteBranch(long id) throws EntityNotFoundException
    {
        if(organizationContactRepos.findById(id).isPresent())
        {
            organizationContactRepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }
}
