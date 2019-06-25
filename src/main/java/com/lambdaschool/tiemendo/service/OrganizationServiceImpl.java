package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
import com.lambdaschool.tiemendo.model.Organization;
import com.lambdaschool.tiemendo.model.OrganizationBranch;
import com.lambdaschool.tiemendo.repository.OrganizationBranchRepository;
import com.lambdaschool.tiemendo.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private OrganizationBranchRepository organizationContactRepos;


    @Override
    public List<Organization> findAll(Pageable pageable, boolean lead)
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
    public List<Organization> searchOrganizations(Pageable pageable, String name, String location, boolean lead) throws ResourceNotFoundException
    {
        var results = new ArrayList<Organization>();

        if (name != null && !name.equals("") && location != null && !location.equals("")){
            organizationRepos.searchOrganizationsByNameAndLocation(pageable, name, location, lead).iterator().forEachRemaining(results::add);
        } else if (name != null && !name.equals("")) {
            organizationRepos.searchOrganizationsByName(pageable, name, lead).iterator().forEachRemaining(results::add);
        } else if (location != null && !location.equals("")) {
            organizationRepos.searchOrganizationsByLocation(pageable, location, lead).iterator().forEachRemaining(results::add);
        } else {
            // if no search critieria return find all
            return findAll(pageable, lead);
        }

//        // todo: make this a utility or find better way to make pageable.
//        int start = pageable.getPageSize() * pageable.getPageNumber();
//        int end = pageable.getPageSize() * (pageable.getPageNumber() + 1);
//        if (end < results.size()) {
//            end = results.size();
//        }

        return new ArrayList<>(results/*.subList(start, end)*/);
    }


    @Override
    public Organization findOrganizationById(long id) throws EntityNotFoundException
    {
        return organizationRepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }
    
    @Override
    public List<OrganizationBranch> findBranchesByOrganization(long id) throws ResourceNotFoundException
    {
        return organizationContactRepos.findAllByOrganization(findOrganizationById(id));
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
    public List<OrganizationBranch> deleteBranch(long id) throws EntityNotFoundException
    {
        if(organizationContactRepos.findById(id).isPresent())
        {
            //Need to find a better way to do this... Need Org so we can return list of it's branches.. Need org contact so we can find org
            Organization org = organizationRepos.findByBranches(organizationContactRepos.getOrganizationBranchBy(id));
            organizationContactRepos.deleteById(id);
            //need to return list of remaining organizations
            return findBranchesByOrganization(org.getId());
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }
    
    @Override
    public List<OrganizationBranch> saveBranch(long id, OrganizationBranch newBranch)
    {
        Organization org = findOrganizationById(id);
        newBranch.setOrganization(org);
        organizationContactRepos.save(newBranch);

        return findOrganizationById(id).getBranches();
    }
    
    @Override
    public List<OrganizationBranch> updateBranch(long id, OrganizationBranch branch) throws ResourceNotFoundException
    {
        Optional<OrganizationBranch> branchOptional = organizationContactRepos.findById(id);
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
            
            organizationContactRepos.save(original);
            
            return organizationRepos.findByBranches(original).getBranches();
            
        }
        else
        {
            throw new ResourceNotFoundException("Could not find branch with id: " + id);
        }
    }
}
