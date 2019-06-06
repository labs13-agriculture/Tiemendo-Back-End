package com.lambdaschool.tiemendo.repository;

import com.lambdaschool.tiemendo.model.CropType;
import org.springframework.data.repository.CrudRepository;

public interface CropTypeRepository extends CrudRepository<CropType, Long> {
    CropType findByCropName(String name);
    CropType findCropTypeById(long id);
}
