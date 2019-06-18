package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.Installment;
import com.lambdaschool.tiemendo.service.InstallmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/installments")
public class InstallmentController
{


    @Autowired
    private InstallmentService installmentService;


    @GetMapping(value = "/installment-list/{clientid}", produces = {"application/json"})
    public ResponseEntity<?> listAllInstallments(
            @PageableDefault(size=50, sort={"date"}) Pageable pageable,
            @PathVariable Long clientid
    ) {
        List<Installment> myInstallment = installmentService.findAllByClient(clientid);
        return new ResponseEntity<>(myInstallment, HttpStatus.OK);
    }


    @GetMapping(value = "/{installmentId}", produces = {"application/json"})
    public ResponseEntity<?> getInstallmentById(@PathVariable Long installmentId)
    {
        Installment i = installmentService.findInstallmentById(installmentId);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    @PostMapping(value = "/new-installment/{clientId}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewInstallment(@PathVariable Long clientId, @Valid @RequestBody Installment newInstallment) throws URISyntaxException
    {
        Client client = installmentService.save(newInstallment, clientId);
        return new ResponseEntity<>(installmentService.findAllByClient(client.getId()), HttpStatus.OK);
    }


    @PutMapping(value = "/update-installment/{installmentId}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updateInstallmentById(@RequestBody Installment installment, @PathVariable Long installmentId)
    {
        Installment i = installmentService.update(installment, installmentId);
        return new ResponseEntity<>(installmentService.findAllByClient(i.getClient().getId()), HttpStatus.OK);
    }


    @DeleteMapping("/installment/{installmentId}")
    public ResponseEntity<?> deleteTransactionById(@PathVariable Long installmentId)
    {
        Client c = installmentService.findInstallmentById(installmentId).getClient();
        installmentService.delete(installmentId);
        return new ResponseEntity<>(installmentService.findAllByClient(c.getId()), HttpStatus.OK);
    }





}
