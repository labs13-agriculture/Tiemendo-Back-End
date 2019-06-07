package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.Yield;
import com.lambdaschool.tiemendo.view.YieldByFarmerAndCropType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;

public interface YieldRepository extends PagingAndSortingRepository<Yield, Long> {

//    @Query(value = "SELECT COUNT(zooid) as countzoos, z.animalid,animaltype FROM zooanimals  z LEFT JOIN animal a ON z.animalid=a.animalid GROUP BY a.animalid,animaltype", nativeQuery = true)



   @Query(value = "SELECT * FROM (SELECT c.active,c.crop_name,y.* FROM croptype c INNER JOIN yield as y ON c.id = y.crop_id) as combined WHERE farmer_id = ?1 AND upper(crop_name)= ?2",nativeQuery = true)
    ArrayList<YieldByFarmerAndCropType> getYieldByFarmerAndCropType(long farmer_id, String crop_name);
}
