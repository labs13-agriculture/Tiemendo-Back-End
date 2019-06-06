package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.User;
import com.lambdaschool.tiemendo.model.Yield;
import com.lambdaschool.tiemendo.view.YieldByFarmerAndCropType;

import java.util.ArrayList;
import java.util.List;

public interface YieldService {

    List<Yield> findAll();

    Yield findYieldById(long id);

    void delete(long id);

    Yield save(Yield yield);

    Yield update(Yield yield, long id);

    ArrayList<YieldByFarmerAndCropType> getYieldByFarmerAndCropType(long farmer_id, String crop_name);
}
