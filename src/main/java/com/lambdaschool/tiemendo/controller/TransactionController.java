package com.lambdaschool.tiemendo.controller;




//import com.lambdaschool.tiemendo.model.Farmer;
import com.lambdaschool.tiemendo.model.*;

import com.lambdaschool.tiemendo.repository.ClientRepository;
import com.lambdaschool.tiemendo.repository.TransactionRepository;

import com.lambdaschool.tiemendo.service.ClientService;
import com.lambdaschool.tiemendo.service.OrganizationService;
import com.lambdaschool.tiemendo.service.RetailerService;
import com.lambdaschool.tiemendo.service.TransactionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {



    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionService transactionService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    RetailerService retailerService;


    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;
//    @Autowired
//    FarmerService farmerService;

    @ApiOperation(value = "Allows authenticated user to post a transaction to database.")
    @ApiImplicitParam(name = "transaction object", dataType = "Transaction", paramType = "body",
            value = "transaction object")
    @PostMapping(value = "/add/{clientid}")
    public ResponseEntity<?> addNewTransaction(@Valid @RequestBody Transaction transaction,@PathVariable Long clientid) {



        Client client = transactionService.save(transaction,clientid);

        return new ResponseEntity<>(client,HttpStatus.OK);


    }

    @PutMapping(value = "/update/{transactionId}")
    public ResponseEntity<?> updateTransaction(
            @RequestBody
                    Transaction updateTransaction,
            @PathVariable
                    long transactionId)
    {
        transactionService.update(updateTransaction, transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Returns all transactions. Pageable.", response = Transaction.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})

    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<?> listAllTransactions(@PageableDefault(page = 0,
            size = 3) Pageable pageable)
    {
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
    public ResponseEntity<?> findTransactionsByClientId(@PathVariable long id)
    {
        Client client = clientService.findClientById(id);

        return new ResponseEntity<>(client.getTransactions(), HttpStatus.OK);
    }

//    @ApiOperation(value = "Returns transaction based on organization id.", response = Transaction.class)
//    @GetMapping(value = "organization/{id}", produces = {"application/json"})
//    public ResponseEntity<?> findTransactionByOrgtId(@PathVariable long id)
//    {
//        Organization organization = organizationService.findOrganizationById(id);
//        List orgTransactions = organization.getTransactions();
//        return new ResponseEntity<>(orgTransactions, HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "Returns transaction based on retailer id.", response = Transaction.class)
//    @GetMapping(value = "retailer/{id}", produces = {"application/json"})
//    public ResponseEntity<?> findTransactionByRetailerId(@PathVariable long id)
//    {
//        Retailer retailer = retailerService.findRetailerById(id);
//        List retailerTransactions = retailer.getTransactions();
//        return new ResponseEntity<>(retailerTransactions, HttpStatus.OK);
//
//    }
        //needs farmer service and impl
//    @ApiOperation(value = "Returns transaction based on farmer id.", response = Transaction.class)
//    @GetMapping(value = "farmer/{id}", produces = {"application/json"})
//    public ResponseEntity<?> findTransactionByFarmerId(@PathVariable long id)
//    {
//        Farmer farmer = farmerService.findFarmerById(id);
//        List farmerTransactions = farmer.getTransactions();
//        return new ResponseEntity<>(farmerTransactions, HttpStatus.OK);
//
//    }

    @ApiOperation(value = "Deletes transaction based on transaction id.", response = Transaction.class)
    @DeleteMapping("/delete/{transactionId}")
    public ResponseEntity<?> deleteTransactionById(@PathVariable Long transactionId)
    {
        transactionService.delete(transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
