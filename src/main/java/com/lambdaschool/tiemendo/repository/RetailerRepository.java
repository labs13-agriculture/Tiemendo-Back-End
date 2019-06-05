package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Retailer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RetailerRepository extends CrudRepository<Retailer, Long>
{
    Retailer findRetailerById(long id);
    
    Retailer findByName(String name);
    
    @Query(value = "UPDATE retailers SET startyear=:startyear WHERE id=:id", nativeQuery = true)
    void updateRetailer(long id, int startyear);
}
