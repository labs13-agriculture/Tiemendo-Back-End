package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Farmer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FarmerRepository extends PagingAndSortingRepository<Farmer, Long> {

    @Query(value = "SELECT * FROM farmers r, client c, farmerlocations l WHERE c.id = r.id AND r.farmerlocationid = l.farmerlocationid AND c.is_lead=:isLead AND c.name ILIKE :name AND (l.address ILIKE :location OR l.community ILIKE :location OR l.district ILIKE :location OR l.landmark ILIKE :location OR l.region ILIKE :location)", nativeQuery = true)
    List<Farmer> searchFarmers(String name, String location, boolean isLead);
}
