package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.Organization;
import com.lambdaschool.tiemendo.model.OrganizationContact;
import com.lambdaschool.tiemendo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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

    @GetMapping(value = "/{organizationId}", produces = {"application/json"})
    public ResponseEntity<?> getOrganizationById(@PathVariable Long organizationId)
    {
        Organization o = organizationService.findOrganizationById(organizationId);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    @PostMapping(value = "/new-organization", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewOrganization(@Valid @RequestBody Organization newOrganization) throws URISyntaxException
    {
        newOrganization = organizationService.save(newOrganization);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrganizationURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{organizationid}").buildAndExpand(newOrganization).toUri();
        responseHeaders.setLocation(newOrganizationURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


}
