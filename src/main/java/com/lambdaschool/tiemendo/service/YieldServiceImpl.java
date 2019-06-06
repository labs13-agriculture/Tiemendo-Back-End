package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.model.Yield;
import com.lambdaschool.tiemendo.repository.YieldRepository;
import com.lambdaschool.tiemendo.view.YieldByFarmerAndCropType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "yieldservice")
public class YieldServiceImpl implements YieldService {

    @Autowired
    YieldRepository yieldRepository;

    @Transactional
    @Override
    public List<Yield> findAll()
    {
        List<Yield> yields = new ArrayList<>();
        yieldRepository.findAll().iterator().forEachRemaining(yields::add);
        return yields;
    }

    @Override
    public Yield findYieldById(long id) {
        return yieldRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id) {
        yieldRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        yieldRepository.deleteById(id);
    }

    @Override
    public Yield save(Yield itemType) {


        return yieldRepository.save(itemType);

    }

    @Override
    @Transactional
    public Yield update(Yield yield, long id) {
        Yield currentYield = yieldRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (yield.getSeason() != null)
        {

            currentYield.setSeason(yield.getSeason());
        }

        if (yield.getNumBags() != null)
        {
            currentYield.setNumBags(yield.getNumBags());
        }

        if (yield.getCropType() != null)
        {
            currentYield.setCropType(yield.getCropType());
        }

        if (yield.getFarmAcres() != null)
        {
            currentYield.setFarmAcres(yield.getFarmAcres());
        }

        if (yield.getFarmer() != null)
        {
            currentYield.setFarmer(yield.getFarmer());
        }

        if (yield.getGoal() != null)
        {
            currentYield.setGoal(yield.getGoal());
        }

        return yieldRepository.save(currentYield);}


    @Override
    public ArrayList<YieldByFarmerAndCropType> getYieldByFarmerAndCropType(long farmer_id, String crop_name) {
        String caseInsensitiveConversion = crop_name.toUpperCase();
        return yieldRepository.getYieldByFarmerAndCropType(farmer_id,caseInsensitiveConversion);
    }
}
