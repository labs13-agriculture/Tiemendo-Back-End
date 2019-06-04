package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
import com.lambdaschool.tiemendo.model.Retailer;
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
            return retailerRepos.findRetailerByRetailerid(id);
        }
        else
        {
            throw new ResourceNotFoundException("Could not find retailer with id: " + id);
        }
    }
}
