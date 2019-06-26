package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.model.Installment;
import com.lambdaschool.tiemendo.service.InstallmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/installments")
public class InstallmentController extends AbstractController
{


    @Autowired
    private InstallmentService installmentService;

    // Lists all installments for a given client
    @GetMapping(value = "/installment-list/{clientid}", produces = {"application/json"})
    public ResponseEntity<?> listAllInstallments(
            @PageableDefault(size=25, sort={"datePaid"}) Pageable pageable,
            @PathVariable Long clientid,
            PagedResourcesAssembler<Installment> assembler
    ) {
        Page<Installment> p = installmentService.findAllByClient(pageable, clientid);
        var content = getContents(p, assembler);
        var headers = getHeaders(p, assembler);

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }


    @GetMapping(value = "/{installmentId}", produces = {"application/json"})
    public ResponseEntity<?> getInstallmentById(@PathVariable Long installmentId)
    {
        Installment i = installmentService.findInstallmentById(installmentId);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    @PostMapping(value = "/new-installment/{clientId}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewInstallment(
            @PathVariable Long clientId,
            @Valid @RequestBody Installment newInstallment,
            @PageableDefault(size=25, sort={"datePaid"}) Pageable pageable,
            PagedResourcesAssembler<Installment> assembler
    ) {

        Page<Installment> p = installmentService.save(pageable, newInstallment, clientId);
        var content = getContents(p, assembler);
        var headers = getHeaders(p, assembler);

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }


    @PutMapping(value = "/update-installment/{installmentId}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updateInstallmentById(
            @RequestBody Installment installment,
            @PathVariable Long installmentId,
            @PageableDefault(size=25, sort={"datePaid"}) Pageable pageable,
            PagedResourcesAssembler<Installment> assembler
    ) {
        Page<Installment> page = installmentService.update(pageable, installment, installmentId);
        var content = getContents(page, assembler);
        var headers = getHeaders(page, assembler);

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }


    @DeleteMapping("/installment/{installmentId}")
    public ResponseEntity<?> deleteTransactionById(
            @PathVariable Long installmentId,
            @PageableDefault(size=25, sort={"datePaid"}) Pageable pageable,
            PagedResourcesAssembler<Installment> assembler
    ) {
        Page<Installment> page = installmentService.delete(pageable, installmentId);

        var content = getContents(page, assembler);
        var headers = getHeaders(page, assembler);

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }





}
