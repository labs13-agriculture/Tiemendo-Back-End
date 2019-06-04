package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.Organization;
import com.lambdaschool.tiemendo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController
{
    @Autowired
    private OrganizationService organizationService;

    @GetMapping(value = "organizations-list", produces = {"application/json"})
    public ResponseEntity<?> listAllOrganizations()
    {
        List<Organization> myOrganization = organizationService.findAll();
        return new ResponseEntity<>(myOrganization, HttpStatus.OK);
    }
}
