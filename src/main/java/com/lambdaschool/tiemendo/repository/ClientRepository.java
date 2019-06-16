package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long>
{
    /*
    List<Client> findClientsByFirstNameContainsOrSecondNameContainsIgnoreCase(String first, String second);
    List<Client> findClientsByAddressContainsOrRegionContainsOrDistrictContainsOrCommunityContainsOrLandmarkContainsIgnoreCase(
            String address, String region, String district, String community, String landmark
    );
    List<Client> findClientsByAddressContainsOrRegionContainsOrDistrictContainsOrCommunityContainsOrLandmarkContainsAndFirstNameContainsOrSecondNameContainsIgnoreCase(
            String address, String region, String district, String community, String landmark, String first, String second
    );
    */

    // uses JPQL instead of native to make request
    @Query("SELECT c FROM Client c WHERE upper(c.firstName) LIKE upper(CONCAT('%',:name,'%')) OR upper(c.secondName) LIKE upper(CONCAT('%',:name,'%'))")
    List<Client> searchByNameFields(String name);

    @Query("SELECT c FROM Client c WHERE " +
                   "upper(c.address) LIKE upper(CONCAT('%',:location,'%')) " +
                   "OR upper(c.community) LIKE upper(CONCAT('%',:location,'%')) " +
                   "OR upper(c.region) LIKE upper(CONCAT('%',:location,'%')) " +
                   "OR upper(c.landmark) LIKE upper(CONCAT('%',:location,'%'))"
    )
    List<Client> searchByLocationFields(String location);

    @Query("SELECT c FROM Client c WHERE " +
                   "(upper(c.firstName) LIKE upper(CONCAT('%',:name,'%')) " +
                   "OR upper(c.secondName) LIKE upper(CONCAT('%',:name,'%'))" +
                   ") AND (upper(c.address) LIKE upper(CONCAT('%',:location,'%')) " +
                   "OR upper(c.community) LIKE upper(CONCAT('%',:location,'%')) " +
                   "OR upper(c.region) LIKE upper(CONCAT('%',:location,'%')) " +
                   "OR upper(c.landmark) LIKE upper(CONCAT('%',:location,'%')))"
    )
    List<Client> searchByNameAndLocationFields(String name, String location);

    List<Client> findClientsByTypeIgnoreCase(Pageable pageable, String type);
}
