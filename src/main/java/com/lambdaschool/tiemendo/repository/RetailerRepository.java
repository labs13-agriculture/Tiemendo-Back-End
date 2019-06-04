package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Retailer;
import org.springframework.data.repository.CrudRepository;

public interface RetailerRepository extends CrudRepository<Retailer, Long>
{
    Retailer findRetailerByRetailerid(long id);
    
    Retailer findByName(String name);
}
