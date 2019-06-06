package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
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
        //sort list alphabetically
        list.sort((c1, c2) -> c2.getCropName().compareToIgnoreCase(c1.getCropName()));
        return list;
    }
    
    @Override
    public CropType getCropTypeById(long id) throws ResourceNotFoundException
    {
        if(cropTypeRepository.existsById(id))
        {
            return cropTypeRepository.findCropTypeById(id);
        }
        else
        {
            throw new ResourceNotFoundException("Could not find CropType with id: " + id);
        }
    }
}
