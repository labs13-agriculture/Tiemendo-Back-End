package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Retailer;

import java.util.List;

public interface RetailerService
{

    List<Retailer> findAll();
    
    List<Retailer> searchRetailer(String name, String location, boolean lead);
    
    Retailer findRetailerById(long id);

}
