package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.CropType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CropTypeRepository extends CrudRepository<CropType, Long> {
    CropType findByCropName(String name);
    CropType findCropTypeById(long id);
    
    @Query(value = "SELECT id, active, crop_name FROM croptype WHERE id=:id", nativeQuery = true)
    CropType findByIdWithoutYields(long id);
}
