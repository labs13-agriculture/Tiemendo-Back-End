package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Farmer;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;


public interface FarmerService  {
    // Get all Farmers Pageable
    public ArrayList<Farmer> findAllFarmers(Pageable pageable);
    // Get all Farmers by Search
    public List<Farmer> searchFarmers(String name, String location, boolean isLead);
    // Get Farmer
    public Farmer findFarmer(long id);
    // Add Farmer
    public Farmer addFarmer(Farmer farmer);
    // Update Farmer
    public Farmer updateFarmer(Farmer farmer);
    // Delete Farmer
    public void deleteFarmer(long id);
}
