package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.*;

import com.lambdaschool.tiemendo.service.ClientService;
import com.lambdaschool.tiemendo.service.TransactionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController extends AbstractController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    ClientService clientService;

    @ApiOperation(value = "Returns all transactions. Pageable.", response = Transaction.class, responseContainer = "List")
    @ApiImplicitParams({
           @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query", value = "Results page you want to retrieve (0..N)"),
           @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
           @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " +
                                     "Default sort order is ascending. Multiple sort criteria are supported.")})
    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<?> listAllTransactions(
            @PageableDefault(page = 0, size = 25) Pageable pageable,
            PagedResourcesAssembler<Transaction> assembler
    ) {
        List<Transaction> transactions = transactionService.findAll(pageable);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns transaction based on transaction id.", response = Transaction.class)
    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<?> findTransactionById(@PathVariable long id)
    {
        return new ResponseEntity<>(transactionService.findTransactionById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns transaction based on client id.", response = Transaction.class)
    @GetMapping(value = "/client/{id}", produces = {"application/json"})
    public ResponseEntity<?> findTransactionsByClientId(
            @PathVariable long id,
            @PageableDefault(page = 0, size = 25) Pageable pageable,
            PagedResourcesAssembler<Transaction> assembler
    ) {
        Client client = clientService.findById(id);
        return new ResponseEntity<>(client.getTransactions(), HttpStatus.OK);
    }

    @ApiOperation(value = "Allows authenticated user to post a transaction to database.")
    @ApiImplicitParam(name = "transaction object", dataType = "Transaction", paramType = "body", value = "transaction object")
    @PostMapping(value = "/add/{clientid}")
    public ResponseEntity<?> addNewTransaction(
            @Valid @RequestBody Transaction transaction,
            @PathVariable Long clientid,
            @PageableDefault(page = 0, size = 25) Pageable pageable,
            PagedResourcesAssembler<Transaction> assembler
    ) {
        Client c = transactionService.save(transaction, clientid);
        return new ResponseEntity<>(c.getTransactions(), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{transactionId}")
    public ResponseEntity<?> updateTransaction(
            @RequestBody Transaction updateTransaction,
            @PathVariable long transactionId,
            @PageableDefault(page = 0, size = 25) Pageable pageable,
            PagedResourcesAssembler<Transaction> assembler
    ) {
        return new ResponseEntity<>(transactionService.update(updateTransaction, transactionId),HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes transaction based on transaction id.", response = Transaction.class)
    @DeleteMapping("/delete/{transactionId}")
    public ResponseEntity<?> deleteTransactionById(
            @PathVariable Long transactionId,
            @PageableDefault(page = 0, size = 25) Pageable pageable,
            PagedResourcesAssembler<Transaction> assembler
    ) {
        Client c = transactionService.findTransactionById(transactionId).getClient();
        
        transactionService.delete(transactionId);
        return new ResponseEntity<>(c.getTransactions(), HttpStatus.OK);
    }
}
