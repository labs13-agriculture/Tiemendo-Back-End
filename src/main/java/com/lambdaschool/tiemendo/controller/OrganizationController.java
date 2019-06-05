package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.Organization;
import com.lambdaschool.tiemendo.model.OrganizationContact;
import com.lambdaschool.tiemendo.model.OrganizationLocation;
import com.lambdaschool.tiemendo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.service.Contact;

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

    // GET ALL ORGANIZATIONS
    @GetMapping(value = "/organizations-list", produces = {"application/json"})
    public ResponseEntity<?> listAllOrganizations()
    {
        List<Organization> myOrganization = organizationService.findAll();
        return new ResponseEntity<>(myOrganization, HttpStatus.OK);
    }
    // GET ALL CONTACTS
    @GetMapping(value = "/contacts-list", produces = {"application/json"})
    public ResponseEntity<?> listAllContacts()
    {
        List<OrganizationContact> myContact = organizationService.findAllContacts();
        return new ResponseEntity<>(myContact, HttpStatus.OK);
    }
    // GET ALL LOCATIONS
    @GetMapping(value = "/locations-list", produces = {"application/json"})
    public ResponseEntity<?> listAllLocations()
    {
        List<OrganizationLocation> myLocation = organizationService.findAllLocations();
        return new ResponseEntity<>(myLocation, HttpStatus.OK);
    }
    // GET ORGANIZATION BY ID
    @GetMapping(value = "/{organizationId}", produces = {"application/json"})
    public ResponseEntity<?> getOrganizationById(@PathVariable Long organizationId)
    {
        Organization o = organizationService.findOrganizationById(organizationId);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }
    // CREATE NEW ORGANIZATION
    @PostMapping(value = "/new-organization", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewOrganization(@Valid @RequestBody Organization newOrganization) throws URISyntaxException
    {
        newOrganization = organizationService.save(newOrganization);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrganizationURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{organizationid}").buildAndExpand(newOrganization).toUri();
        responseHeaders.setLocation(newOrganizationURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // DELETE ORGANIZATION BY ID
    @DeleteMapping("/organization/{organizationId}")
    public ResponseEntity<?> deleteOrganizationById(@PathVariable Long organizationId)
    {
        organizationService.delete(organizationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // DELETE CONTACT BY ID
    @DeleteMapping("/contact/{contactId}")
    public ResponseEntity<?> deleteContactById(@PathVariable Long contactId)
    {
        organizationService.deleteContact(contactId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // DELETE LOCATION BY ID
    @DeleteMapping("/location/{locationId}")
    public ResponseEntity<?> deleteLocationById(@PathVariable Long locationId)
    {
        organizationService.deleteLocation(locationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // UPDATE ORGANIZATION BY ID
    @PutMapping(value = "/update-organization/{organizationId}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updateOrganizationById(@RequestBody Organization organization, @PathVariable Long organizationId)
    {
        organizationService.update(organization, organizationId);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

}
