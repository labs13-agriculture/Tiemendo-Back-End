package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Organization;
import com.lambdaschool.tiemendo.model.OrganizationBranch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<Organization> searchOrganizationsByName(Pageable pageable, String name, boolean lead);

    @Query(value = "SELECT DISTINCT o from Organization o JOIN o.branches b WHERE " +
            "(upper(o.headquarters) LIKE upper(concat('%', :loc, '%')) " +
            "or upper(b.address) LIKE upper(concat('%', :loc, '%')) " +
            "or upper(b.district) LIKE upper(concat('%', :loc, '%')) " +
            "or upper(b.region) LIKE upper(concat('%', :loc, '%')) " +
            "or upper(b.landmark) LIKE upper(concat('%', :loc, '%'))" +
            ") AND (o.lead = :lead)"
    )
    Page<Organization> searchOrganizationsByLocation(Pageable pageable, String loc, boolean lead);

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
    Page<Organization> searchOrganizationsByNameAndLocation(Pageable pageable, String name, String loc, boolean lead);

    Organization findByBranches(OrganizationBranch branch);
}
