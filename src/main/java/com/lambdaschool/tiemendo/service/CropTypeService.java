package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.CropType;

import java.util.List;

public interface CropTypeService
{
    List<CropType> getAllCropTypes();
    
    CropType getCropTypeById(long id, boolean includeYields);
    
    CropType save(CropType newCrop);
    
    CropType update(long id, CropType update);
    
    void delete(long id);
}
