package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.Organization;
import com.lambdaschool.tiemendo.model.OrganizationBranch;
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
        List<OrganizationBranch> myContact = organizationService.findAllBranches();
        return new ResponseEntity<>(myContact, HttpStatus.OK);
    }
    
    // GET CONTACTS BY ORGANIZATION ID
    @GetMapping(value = "/contacts/{id}", produces = {"application/json"})
    public ResponseEntity<?> listContactsByOrganization(@PathVariable long id)
    {
        return new ResponseEntity<>(organizationService.findBranchesByOrganization(id), HttpStatus.OK);
    }

    // SEARCH
    @GetMapping(value = "/search", produces = {"application/json"})
    public ResponseEntity<?> orgSearch(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue="") String location,
            @RequestParam(defaultValue = "false") boolean lead)
    {
        return new ResponseEntity<>(organizationService.searchOrganizations(name, location, lead), HttpStatus.OK);
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
        organizationService.deleteBranch(contactId);
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
