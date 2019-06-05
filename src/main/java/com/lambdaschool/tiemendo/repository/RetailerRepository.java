package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Retailer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RetailerRepository extends CrudRepository<Retailer, Long>
{
    Retailer findRetailerById(long id);
    
    Retailer findByName(String name);
    
    @Query(value = "UPDATE retailers SET is_lead=:islead, name=:name, startyear= WHERE id=:id", nativeQuery = true)
    void updateRetailer(long id, boolean islead, String name, int startyear);
}
