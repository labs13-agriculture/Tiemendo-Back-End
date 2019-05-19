package com.lambdaschool.tiemendo.controller;


import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ClientController
{

    @Autowired
    private ClientService clientService;

    //localhost:4040/clients
    @GetMapping(value = "/clients", produces = {"application/json"})
    public ResponseEntity<?> listAllClients()
    {
        ArrayList<Client> myClients = clientService.findAllClients();
        return new ResponseEntity<>(myClients, HttpStatus.OK);
    }

    //localhost:4040/client/{clientid}
    @GetMapping(value = "/client/{clientid}", produces = {"application/json"})
    public ResponseEntity<?> getClientById(@PathVariable Long clientid)
    {
        Client r = clientService.findClientById(clientid);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

}
