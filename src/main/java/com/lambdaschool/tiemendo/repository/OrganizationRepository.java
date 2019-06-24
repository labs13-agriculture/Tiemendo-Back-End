package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Organization;
import com.lambdaschool.tiemendo.model.OrganizationBranch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long>
{
    @Query(value = "SELECT DISTINCT o from Organization o JOIN o.branches b WHERE " +
            "(upper(o.name) LIKE upper(concat('%', :name, '%')) " +
            "or upper(b.name) LIKE upper(concat('%', :name, '%'))) " +
            " AND (o.lead = :lead)"
    )
    List<Organization> searchOrganizationsByName(String name, boolean lead);

    @Query(value = "SELECT DISTINCT o from Organization o JOIN o.branches b WHERE " +
            "(upper(o.headquarters) LIKE upper(concat('%', :loc, '%')) " +
            "or upper(b.address) LIKE upper(concat('%', :loc, '%')) " +
            "or upper(b.district) LIKE upper(concat('%', :loc, '%')) " +
            "or upper(b.region) LIKE upper(concat('%', :loc, '%')) " +
            "or upper(b.landmark) LIKE upper(concat('%', :loc, '%'))" +
            ") AND (o.lead = :lead)"
    )
    List<Organization> searchOrganizationsByLocation(String loc, boolean lead);

    @Query(value = "SELECT DISTINCT o from Organization o JOIN o.branches b WHERE " +
            "(upper(o.name) LIKE upper(concat('%', :name, '%')) " +
            "or upper(b.name) LIKE upper(concat('%', :name, '%'))" +
            ") AND (upper(o.headquarters) LIKE upper(concat('%', :loc, '%')) " +
            "or upper(b.address) LIKE upper(concat('%', :loc, '%')) " +
            "or upper(b.district) LIKE upper(concat('%', :loc, '%')) " +
            "or upper(b.region) LIKE upper(concat('%', :loc, '%')) " +
            "or upper(b.landmark) LIKE upper(concat('%', :loc, '%'))" +
            ") AND (o.lead = :lead)"
    )
    List<Organization> searchOrganizationsByNameAndLocation(String name, String loc, boolean lead);


    List<Organization> searchOrganizations(String name, String location, boolean isLead);
    
    Organization findByBranches(OrganizationBranch branch);
}
