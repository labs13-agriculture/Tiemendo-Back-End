package com.lambdaschool.tiemendo.controller;


import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.Staff;
import com.lambdaschool.tiemendo.service.ClientService;
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
public class ClientController
{

    @Autowired
    private ClientService clientService;


    // ###########################################################################################
    //localhost:4040/clients
    @GetMapping(value = "/clients", produces = {"application/json"})
    public ResponseEntity<?> listAllClients()
    {
        ArrayList<Client> myClients = clientService.findAllClients();
        return new ResponseEntity<>(myClients, HttpStatus.OK);
    }
    // ###########################################################################################



    // ###########################################################################################
    //localhost:4040/client/{clientid}
    @GetMapping(value = "/client/{clientid}", produces = {"application/json"})
    public ResponseEntity<?> getClientById(@PathVariable Long clientid)
    {
        Client r = clientService.findClientById(clientid);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }
    // ###########################################################################################

    @PostMapping(value = "/client",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addNewClient(@Valid
                                          @RequestBody
                                          Client newClient) throws URISyntaxException
    {
        newClient = clientService.addNewClient(newClient);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newClientURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{clientid}").buildAndExpand(newClient.getClientid()).toUri();
        responseHeaders.setLocation(newClientURI);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
    // ###########################################################################################

    // ###########################################################################################

    @PutMapping(value = "client/{id}")
    public ResponseEntity<?> updateClientWithId(@Valid @RequestBody Client client, @PathVariable long id)
    {
    return new ResponseEntity<>(clientService.updateClient(client, id), HttpStatus.OK);
    }

    // ###########################################################################################

    // ###########################################################################################

    @DeleteMapping(value = "client/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable long id)
    {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }







}
