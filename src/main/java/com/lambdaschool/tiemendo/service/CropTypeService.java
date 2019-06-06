package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.CropType;

import java.util.List;

public interface CropTypeService
{
    List<CropType> getAllCropTypes();
    
    CropType getCropTypeById(long id);
}
