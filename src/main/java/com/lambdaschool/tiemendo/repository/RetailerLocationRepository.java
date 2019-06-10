package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.RetailerLocation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RetailerLocationRepository extends PagingAndSortingRepository<RetailerLocation, Long>
{
    @Modifying
    @Query(value = "UPDATE retailerlocations SET address=:address, community=:community, district=:district, landmark=:landmark, region=:region WHERE retailerlocationid=:id", nativeQuery = true)
    void updateLocation(long id, String address, String community, String district, String landmark, String region);
}
