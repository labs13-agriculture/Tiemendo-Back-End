package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.CropType;
import com.lambdaschool.tiemendo.repository.CropTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "cropTypeService")
public class CropTypeServiceImpl implements CropTypeService
{
    @Autowired
    private CropTypeRepository cropTypeRepository;
    
    @Override
    public List<CropType> getAllCropTypes()
    {
        List<CropType> list = new ArrayList<>();
        cropTypeRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
