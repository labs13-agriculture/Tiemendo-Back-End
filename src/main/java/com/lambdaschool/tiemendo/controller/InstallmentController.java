package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.Installment;
import com.lambdaschool.tiemendo.service.InstallmentService;
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
public class InstallmentController
{


    @Autowired
    private InstallmentService installmentService;


    @GetMapping(value = "/installment-list", produces = {"application/json"})
    public ResponseEntity<?> listAllInstallments()
    {
        List<Installment> myInstallment = installmentService.findAll();
        return new ResponseEntity<>(myInstallment, HttpStatus.OK);
    }


    @GetMapping(value = "/installment/{installmentId}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> getInstallmentById(@PathVariable Long installmentId)
    {
        Installment i = installmentService.findInstallmentById(installmentId);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    @PostMapping(value = "/new-installment/{clientId}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewInstallment(@PathVariable Long clientId, @Valid @RequestBody Installment newInstallment) throws URISyntaxException
    {
        Client client = installmentService.save(newInstallment, clientId);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }


    @PutMapping(value = "/update-installment/{installmentId}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updateInstallmentById(@RequestBody Installment installment, @PathVariable Long installmentId)
    {
        installmentService.update(installment, installmentId);
        return new ResponseEntity<>(installment, HttpStatus.OK);
    }


    @DeleteMapping("/installment/{installmentId}")
    public ResponseEntity<?> deleteTransactionById(@PathVariable Long installmentId)
    {
        installmentService.delete(installmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
