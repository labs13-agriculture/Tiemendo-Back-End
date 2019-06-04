package com.lambdaschool.tiemendo.service;


import com.lambdaschool.tiemendo.model.Retailer;
import com.lambdaschool.tiemendo.model.RetailerLocation;
import com.lambdaschool.tiemendo.repository.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "retailerService")
public class RetailerServiceImpl implements RetailerSerivce
{

    @Autowired
    private RetailerRepository retailerRepos;

    @Transactional
    @Override
    public List<Retailer> findAll()
    {
        List<Retailer> list = new ArrayList<>();
        retailerRepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }



}
