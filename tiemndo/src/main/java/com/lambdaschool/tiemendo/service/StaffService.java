package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.model.Staff;

import java.util.ArrayList;
import java.util.List;

public interface StaffService
{

    Staff addNewStaffMember(Staff staff);

    ArrayList<Staff> findAllStaffMembers();

    List<Staff> findStaffMemeberById(long id);

}
