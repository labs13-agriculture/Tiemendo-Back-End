package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
import com.lambdaschool.tiemendo.model.Retailer;
import com.lambdaschool.tiemendo.model.RetailerContact;
import com.lambdaschool.tiemendo.model.RetailerLocation;
import com.lambdaschool.tiemendo.repository.RetailerContactRepository;
import com.lambdaschool.tiemendo.repository.RetailerLocationRepository;
import com.lambdaschool.tiemendo.repository.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "retailerService")
public class RetailerServiceImpl implements RetailerService
{

    @Autowired
    private RetailerRepository retailerRepos;

    @Autowired
    private RetailerContactRepository retailerContactRepository;
    
    @Autowired
    private RetailerLocationRepository retailerLocationRepository;
    
    @Transactional
    @Override
    public List<Retailer> findAll()
    {
        List<Retailer> list = new ArrayList<>();
        retailerRepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
    
    @Override
    public List<Retailer> searchRetailer(String name, String location, boolean lead)
    {
        List<Retailer> list = new ArrayList<>();
        //retailerRepos.searchRetailers(name, location, lead).forEachRemaining(list::add);
        return list;
    }
    
    @Override
    public Retailer findRetailerById(long id) throws ResourceNotFoundException
    {
        if (retailerRepos.findById(id).isPresent())
        {
            return retailerRepos.findRetailerById(id);
        }
        else
        {
            throw new ResourceNotFoundException("Could not find retailer with id: " + id);
        }
    }
    
    @Transactional
    @Override
    public Retailer save(Retailer newRetailer)
    {
        retailerLocationRepository.save(newRetailer.getRetailerlocation());
        retailerContactRepository.save(newRetailer.getRetailercontact());
        return retailerRepos.save(newRetailer);
    }
    
    @Transactional
    @Override
    public Retailer update(long id, Retailer update) throws ResourceNotFoundException
    {
        //make sure the retailer we're looking to update exists
        if (retailerRepos.findById(id).isPresent())
        {
            //Only startyear is in retailers table
            retailerRepos.updateRetailer(id, update.getStartyear());
            
            //Update the rest of retailer in client table
            
            //update the retailercontacts table - HERE'S WHY WE NEED ID INCLUDED
            //make sure there is a retailercontact with a valid id
            RetailerContact rc = update.getRetailercontact();
            if(rc.getRetailercontactid() != 0)
            {//id,  dob,  education,  email, gender,  name,  nationality,  phone,  position,  title
                retailerContactRepository.update(rc.getRetailercontactid(), rc.getDateofbirth(), rc.getEducationlevel(), rc.getEmail(), rc.getGender(), rc.getName(), rc.getNationality(), rc.getPhone(), rc.getPosition(), rc.getTitle());
            }
            else{ // it is a new retailer contact
                retailerContactRepository.save(rc);
            }
            
            //update the retailerLocations table - HERE'S WHY WE NEED ID INCLUDED
            RetailerLocation rl = update.getRetailerlocation();
            if(rl.getRetailerlocationid() != 0)
            {
                retailerLocationRepository.updateLocation(id, rl.getAddress(), rl.getCommunity(), rl.getDistrict(), rl.getLandmark(), rl.getRegion());
            }
            else
            {
                retailerLocationRepository.save(rl);
            }
            
        }
        else
        {
            throw new ResourceNotFoundException("Could not find retailer with id: " + id);
        }
        return null;
    }
    
    @Override
    public void delete(long id) throws ResourceNotFoundException
    {
        if(retailerRepos.findById(id).isPresent())
        {
            retailerRepos.deleteById(id);
        }else
        {
            throw new ResourceNotFoundException("Could not find retailer with id: " + id);
        }
    }
}
