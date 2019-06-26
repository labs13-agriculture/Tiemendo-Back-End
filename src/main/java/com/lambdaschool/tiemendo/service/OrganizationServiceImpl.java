package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
import com.lambdaschool.tiemendo.model.Organization;
import com.lambdaschool.tiemendo.model.OrganizationBranch;
import com.lambdaschool.tiemendo.repository.OrganizationBranchRepository;
import com.lambdaschool.tiemendo.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "organizationService")
public class OrganizationServiceImpl implements OrganizationService
{

    @Autowired
    private OrganizationRepository organizationRepos;

    @Autowired
    private OrganizationBranchRepository branchRepo;


    @Override
    public Page<Organization> findAll(Pageable pageable, boolean lead)
    {
        return organizationRepos.findAllByLead(pageable, lead);
    }

    @Override
    public Page<Organization> searchOrganizations(Pageable pageable, String name, String location, boolean lead) throws ResourceNotFoundException
    {
        Page<Organization> results;

        if (name != null && !name.equals("") && location != null && !location.equals("")){
            results = organizationRepos.searchOrganizationsByNameAndLocation(pageable, name, location, lead);
        } else if (name != null && !name.equals("")) {
            results = organizationRepos.searchOrganizationsByName(pageable, name, lead);
        } else if (location != null && !location.equals("")) {
            results = organizationRepos.searchOrganizationsByLocation(pageable, location, lead);
        } else {
            // if no search critieria return find all
            return findAll(pageable, lead);
        }

        return results;
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

    /*
    *
    *  After This is Methods for OrganizationBranches
    *
    * */

    @Override
    public Page<OrganizationBranch> findAllBranches(Pageable pageable)
    {
        return branchRepo.findAll(pageable);
    }

    @Override
    public Page<OrganizationBranch> findBranchesByOrganization(Pageable pageable, long id)
    {
        return branchRepo.findAllByOrganization(pageable, findOrganizationById(id));
    }

    @Transactional
    @Override
    public Page<OrganizationBranch> deleteBranch(Pageable pageable, long id)
    {
        
        Optional<OrganizationBranch> organizationBranch = branchRepo.findById(id);
        if(organizationBranch.isPresent()) // see if the organization branch exists
        {
            long orgId = organizationBranch.get().getOrganization().getId();
            branchRepo.delete(organizationBranch.get());
            return findBranchesByOrganization(pageable, orgId);
        } else
        {
            throw new ResourceNotFoundException("Could not find branch with ID: " + id);
        }
        
    }
    
    @Override
    public Page<OrganizationBranch> saveBranch (Pageable pageable, long id, OrganizationBranch newBranch)
    {
        Organization org = findOrganizationById(id);
        newBranch.setOrganization(org);
        branchRepo.save(newBranch);

        return findBranchesByOrganization(pageable, id);
    }
    
    @Override
    public Page<OrganizationBranch> updateBranch(Pageable pageable, long id, OrganizationBranch branch)
    {
        Optional<OrganizationBranch> branchOptional = branchRepo.findById(id);
        if(branchOptional.isPresent())
        {
            OrganizationBranch original = branchOptional.get();
            original.setName(branch.getName());
            original.setPosition(branch.getPosition());
            original.setEmail(branch.getEmail());
            original.setPhone(branch.getPhone());
    
            original.setAddress(branch.getAddress());
            original.setDistrict(branch.getDistrict());
            original.setLandmark(branch.getLandmark());
            original.setRegion(branch.getRegion());
            
            branchRepo.save(original);
            
            return findBranchesByOrganization(pageable, original.getOrganization().getId());
            
        }
        else
        {
            throw new ResourceNotFoundException("Could not find branch with id: " + id);
        }
    }
}
