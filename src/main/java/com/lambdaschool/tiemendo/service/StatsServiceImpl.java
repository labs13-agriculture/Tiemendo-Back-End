package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.repository.FarmerRepository;
import com.lambdaschool.tiemendo.view.CountFarmerAndGender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "statsService")
public class StatsServiceImpl implements StatsService
{
    @Autowired
    private FarmerRepository farmerRepository;
    
    @Override
    public CountFarmerAndGender getCountFarmerAndGender()
    {
        return farmerRepository.getCountFarmerAndGender();
    }
}
