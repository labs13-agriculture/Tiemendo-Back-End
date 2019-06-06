package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Farmer;
import com.lambdaschool.tiemendo.repository.FarmerRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Service("farmerService")
public class FarmerServiceImpl implements FarmerService {

    FarmerRepository farmerRepo;

    public FarmerServiceImpl(FarmerRepository farmerRepo) {
        // Construction injection over autowired because this field is required.
        this.farmerRepo = farmerRepo;
    }

    @Override
    public ArrayList<Farmer> findAllFarmers(Pageable pageable) {
        var farmers = new ArrayList<Farmer>();
        farmerRepo.findAll(pageable).iterator().forEachRemaining(farmers::add);

        return farmers;
    }

    @Override
    public ArrayList<Farmer> findFarmerWith(Pageable pageable, String search) {
        return null;
    }

    @Override
    public Farmer findFarmer(long id) {
        return null;
    }

    @Override
    public Farmer addFarmer(Farmer farmer) {
        return null;
    }

    @Override
    public Farmer updateFarmer(Farmer farmer) {
        return null;
    }

    @Override
    public void Farmer(long id) {

    }
}
