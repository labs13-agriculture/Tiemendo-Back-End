package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.exception.IllegalDeleteException;
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
    public List<CropType> getAllCropTypes(String cropStatus)
    {
        System.out.println(cropStatus);
        List<CropType> list = new ArrayList<>();
        //sort list alphabetically
        cropTypeRepository.findAll().iterator().forEachRemaining(list::add);
        list.sort((c1, c2) -> c1.getCropName().compareToIgnoreCase(c2.getCropName()));
        //Without setting yields to null, list will inlcude yeilds, which includes farmers, which includes installments, etc. This does not affect yields stored in database
        list.forEach(c -> c.setYields(null));
        
        //filter out active vs inactive if needed
        if(cropStatus.equalsIgnoreCase("active"))
        {
            List<CropType> newList = new ArrayList<>();
            for (CropType c : list)
            {
                if (c.isActive())
                {
                    newList.add(c);
                }
            }
            return newList;
        }
        else if (cropStatus.equalsIgnoreCase("inactive"))
        {
            List<CropType> newList = new ArrayList<>();
            for (CropType c : list)
            {
                if (!c.isActive())
                {
                    newList.add(c);
                }
            }
            return newList;
        }
        
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
            //can't just save this because front-end may not have, and therefore may not send, yield data
            CropType original = cropTypeRepository.findCropTypeById(id);
            original.setCropName(update.getCropName());
            original.setActive(update.isActive());
            return cropTypeRepository.save(original);
        } else
        {
            throw new ResourceNotFoundException("Could not find CropType with id: " + id);
        }
    }
    
    @Override
    public void delete(long id)
    {
        CropType c = getCropTypeById(id, true);
        if(c.getYields().size() > 0)
        {
            throw new IllegalDeleteException("Could not delete CropType with yields attached");
        }else
        {
            cropTypeRepository.delete(c);
        }
    }
}
