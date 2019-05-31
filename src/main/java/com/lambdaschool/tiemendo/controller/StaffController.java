package com.lambdaschool.tiemendo.controller;


import com.lambdaschool.tiemendo.model.Staff;
import com.lambdaschool.tiemendo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;


@RestController
public class StaffController
{
    @Autowired
    private StaffService staffService;

    // ###########################################################################################
    //localhost:4040/staff
    @GetMapping(value = "/staff", produces = {"application/json"})
    public ResponseEntity<?> listAllStaffMembers()
    {
        ArrayList<Staff> myStaffMembers = staffService.findAllStaffMembers();
        return new ResponseEntity<>(myStaffMembers, HttpStatus.OK);
    }
    // ###########################################################################################



    // ###########################################################################################
    //localhost:4040/staff/{staffid}
    @GetMapping(value = "/staff/{staffid}", produces = {"application/json"})
    public ResponseEntity<?> getStaffById(@PathVariable Long staffid)
    {
        Staff r = staffService.findStaffMemeberById(staffid);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @PostMapping(value = "/staff",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addNewStaffMember(@Valid
                                               @RequestBody
                                               Staff newStaffmember) throws URISyntaxException
    {
        newStaffmember = staffService.addNewStaffMember(newStaffmember);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newStudentURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{staffid}").buildAndExpand(newStaffmember.getStaffid()).toUri();
        responseHeaders.setLocation(newStudentURI);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    // ###########################################################################################
}
