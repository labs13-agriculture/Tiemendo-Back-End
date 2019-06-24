package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long>
{
    // uses JPQL instead of native to make request. resource https://thoughts-on-java.org/jpql/
    @Query("SELECT c FROM Client c WHERE (" +
                   "upper(c.firstName) LIKE upper(CONCAT('%',:name,'%')) " +
                   "OR upper(c.secondName) LIKE upper(CONCAT('%',:name,'%'))" +
                   ") AND (c.lead = :lead) AND (upper(c.type) like upper(CONCAT('%',:type,'%')))"
    )
    List<Client> searchByNameFields(String name, boolean lead, String type);

    @Query("SELECT c FROM Client c WHERE (" +
                   "upper(c.address) LIKE upper(CONCAT('%',:location,'%')) " +
                   "OR upper(c.community) LIKE upper(CONCAT('%',:location,'%')) " +
                   "OR upper(c.region) LIKE upper(CONCAT('%',:location,'%')) " +
                   "OR upper(c.landmark) LIKE upper(CONCAT('%',:location,'%'))" +
                   ") AND (c.lead = :lead) AND (upper(c.type) like upper(CONCAT('%',:type,'%')))"
    )
    List<Client> searchByLocationFields(String location, boolean lead, String type);

    @Query("SELECT c FROM Client c WHERE " +
                   "(upper(c.firstName) LIKE upper(CONCAT('%',:name,'%')) " +
                   "OR upper(c.secondName) LIKE upper(CONCAT('%',:name,'%'))" +
                   ") AND (upper(c.address) LIKE upper(CONCAT('%',:location,'%')) " +
                   "OR upper(c.community) LIKE upper(CONCAT('%',:location,'%')) " +
                   "OR upper(c.region) LIKE upper(CONCAT('%',:location,'%')) " +
                   "OR upper(c.landmark) LIKE upper(CONCAT('%',:location,'%'))" +
                   ") AND (c.lead = :lead) AND (upper(c.type) like upper(CONCAT('%',:type,'%')))"
    )
    List<Client> searchByNameAndLocationFields(String name, String location, boolean lead, String type);

    List<Client> findClientsByTypeAndLead(Pageable pageable, String type, boolean lead);
}
