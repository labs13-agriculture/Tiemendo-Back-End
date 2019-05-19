package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Staff;
import com.lambdaschool.tiemendo.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;



@Service(value = "staffService")
public class StaffServiceImpl implements StaffService
{

    @Autowired
    private StaffRepository staffrepos;

    @Override
    public Staff addNewStaffMember(Staff staff)
    {
        return null;
    }

    @Override
    public ArrayList<Staff> findAllStaffMembers()
    {
        ArrayList<Staff> list = new ArrayList<>();
        staffrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public ArrayList<Staff> findStaffMemeberById(long id)
    {
        return null;
    }
}
