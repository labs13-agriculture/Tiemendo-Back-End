package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long>
{
    @Query(value = "SELECT * FROM organizations o, client c INNER JOIN organizationlocations l ON l.organizationid=c.id WHERE c.id = o.id AND o.id = l.organizationid AND c.is_lead=:isLead AND c.name ILIKE :name AND (l.address ILIKE :location OR l.district ILIKE :location OR l.community ILIKE :location OR l.landmark ILIKE :location OR l.region ILIKE :location)", nativeQuery = true)
    List<Organization> searchOrganizations(String name, String location, boolean isLead);
}
