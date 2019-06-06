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
        list.sort((c1, c2) -> c1.getCropName().compareToIgnoreCase(c2.getCropName()));
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
    
    @Override
    public CropType save(CropType newCrop)
    {
        return cropTypeRepository.save(newCrop);
    }
    
    @Override
    public CropType update(long id, CropType update) throws ResourceNotFoundException
    {
        if (cropTypeRepository.existsById(id))
        {
            return cropTypeRepository.save(update);
        } else
        {
            throw new ResourceNotFoundException("Could not find CropType with id: " + id);
        }
    }
}
