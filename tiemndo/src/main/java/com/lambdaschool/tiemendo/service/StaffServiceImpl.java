package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Staff;
import com.lambdaschool.tiemendo.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;



@Service(value = "staffService")
public class StaffServiceImpl implements StaffService
{

    @Autowired
    private StaffRepository staffrepos;


    @Transactional
    @Override
    public Staff addNewStaffMember(Staff staff)
    {
        Staff newStaffMember = new Staff();

        newStaffMember.setStaffname(staff.getStaffname());

        return staffrepos.save(newStaffMember);
    }

    @Override
    public ArrayList<Staff> findAllStaffMembers()
    {
        ArrayList<Staff> list = new ArrayList<>();
        staffrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Staff findStaffMemeberById(long id) throws EntityNotFoundException
    {
        return staffrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }
}
