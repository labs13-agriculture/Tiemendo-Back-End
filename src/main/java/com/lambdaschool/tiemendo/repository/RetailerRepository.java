package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Retailer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RetailerRepository extends CrudRepository<Retailer, Long>
{
    Retailer findRetailerById(long id);
    
    Retailer findByName(String name);
    
    @Modifying
    @Query(value = "UPDATE retailers SET startyear=:startyear WHERE id=:id", nativeQuery = true)
    void updateRetailer(long id, int startyear);
    
    @Query(value = "SELECT * FROM retailers r, client c, retailerlocations l WHERE c.id = r.id AND r.retailerlocationid = l.retailerlocationid AND c.is_lead=:isLead AND c.name ILIKE :name AND (l.address ILIKE :location OR l.community ILIKE :location OR l.district ILIKE :location OR l.landmark ILIKE :location OR l.region ILIKE :location)", nativeQuery = true)
    List<Retailer> searchRetailers(String name, String location, boolean isLead);
}
