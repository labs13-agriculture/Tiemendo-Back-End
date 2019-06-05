package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
import com.lambdaschool.tiemendo.model.Retailer;
import com.lambdaschool.tiemendo.model.RetailerContact;
import com.lambdaschool.tiemendo.model.RetailerLocation;
import com.lambdaschool.tiemendo.repository.ClientRepository;
import com.lambdaschool.tiemendo.repository.RetailerContactRepository;
import com.lambdaschool.tiemendo.repository.RetailerLocationRepository;
import com.lambdaschool.tiemendo.repository.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Transactional
    @Override
    public List<Retailer> findAll()
    {
        List<Retailer> list = new ArrayList<>();
        retailerRepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
    
    @Override
    public List<Retailer> searchRetailer(String name, String location, boolean lead) throws ResourceNotFoundException
    {
        //SELECT * FROM retailers r, client c, retailerlocations l WHERE c.id = r.id AND r.retailerlocationid = l.retailerlocationid AND c.name ILIKE 'a%' AND (l.address ILIKE 'AB%' OR l.community ILIKE 'AB%' OR l.district ILIKE 'AB%' OR l.landmark ILIKE 'AB%' OR l.region ILIKE 'AB%')
        // % is a wildcard, so other characters can exist before and after search parameter
        String nameSearchParam = "%" + name + "%";
        String locationSearchParam = "%" + location + "%";
        List<Retailer> results = retailerRepos.searchRetailers(nameSearchParam, locationSearchParam, lead);
        if (results.size() != 0)
        {
            return results;
        }
        else
        {
            throw new ResourceNotFoundException("No Retailers found matching search terms");
        }
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
    
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    @Override
    public Retailer update(long id, Retailer update) throws Exception
    {
        //make sure the retailer we're looking to update exists
        if (retailerRepos.findById(id).isPresent())
        {
            
            
            //update the retailercontacts table - HERE'S WHY WE NEED ID INCLUDED
            //make sure there is a retailercontact with a valid id
            RetailerContact rc = update.getRetailercontact();
            if(rc.getRetailercontactid() != 0)
            {//id,  dob,  education,  email, gender,  name,  nationality,  phone,  position,  title
                //System.out.println("Running retailercontact repository");
                retailerContactRepository.update(rc.getRetailercontactid(), rc.getDateofbirth(), rc.getEducationlevel(), rc.getEmail(), rc.getGender(), rc.getName(), rc.getNationality(), rc.getPhone(), rc.getPosition(), rc.getTitle());
            }
            else{ // it is a new retailer contact
                //System.out.println("Running retailercontact save");
                retailerContactRepository.save(rc);
            }
            
            //update the retailerLocations table - HERE'S WHY WE NEED ID INCLUDED
            RetailerLocation rl = update.getRetailerlocation();
            if(rl.getRetailerlocationid() != 0)
            {
                System.out.println(rl.getAddress() + " " + rl.getCommunity() + " " + rl.getDistrict());
                System.out.println("Running location update");
                retailerLocationRepository.updateLocation(rl.getRetailerlocationid(), rl.getAddress(), rl.getCommunity(), rl.getDistrict(), rl.getLandmark(), rl.getRegion());
            }
            else
            {
                //System.out.println("Running location save");
                retailerLocationRepository.save(rl);
            }
            //Update the rest of retailer in client table
            //System.out.println("Running client update");
            clientRepository.updateClient(id, update.isLead(), update.getName());
            
            //Only startyear is in retailers table
            //System.out.println("Running retailer update");
            return retailerRepos.save(update);
    
            
            
        }
        else
        {
            throw new ResourceNotFoundException("Could not find retailer with id: " + id);
        }
//        Thread.sleep(750);
    
        
        
//        return null;
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
