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
        //Without setting yields to null, list will inlcude yeilds, which includes farmers, which includes installments, etc. This does not affect yields stored in database
        list.forEach(c -> c.setYields(null));
        return list;
    }
    
    @Override
    public CropType getCropTypeById(long id, boolean includeYields) throws ResourceNotFoundException
    {
        if(cropTypeRepository.existsById(id))
        {
            CropType foundCrop = cropTypeRepository.findCropTypeById(id);
            if(!includeYields)
            {
                // setting yields to null to reduce data sent - in testing, one croptype was 500+ lines because yield was included
                foundCrop.setYields(null);
            }
            return foundCrop;
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
