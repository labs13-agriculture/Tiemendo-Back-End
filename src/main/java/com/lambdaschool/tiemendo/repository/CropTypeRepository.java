package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.CropType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CropTypeRepository extends PagingAndSortingRepository<CropType, Long> {
    CropType findByCropName(String name);
    CropType findCropTypeById(long id);
    
    @Query(value = "SELECT id, active, crop_name FROM croptype WHERE id=:id", nativeQuery = true)
    CropType findByIdWithoutYields(long id);
    
    @Query(value = "SELECT * FROM croptype WHERE active=:active", nativeQuery = true)
    List<CropType> findAllByActive(boolean active);
}
