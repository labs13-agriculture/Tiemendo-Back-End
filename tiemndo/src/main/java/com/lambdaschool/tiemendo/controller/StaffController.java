package com.lambdaschool.tiemendo.controller;


import com.lambdaschool.tiemendo.model.Staff;
import com.lambdaschool.tiemendo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class StaffController
{
    @Autowired
    private StaffService staffService;


    //localhost:4040/staff
    @GetMapping(value = "/staff", produces = {"application/json"})
    public ResponseEntity<?> listAllStaffMembers()
    {
        ArrayList<Staff> myStaffMembers = staffService.findAllStaffMembers();
        return new ResponseEntity<>(myStaffMembers, HttpStatus.OK);
    }
}
