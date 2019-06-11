package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.Transaction;
import com.lambdaschool.tiemendo.model.TransactionItem;
import com.lambdaschool.tiemendo.repository.TransactionItemRepository;
import com.lambdaschool.tiemendo.service.TransactionItemService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transaction-item")
public class TransactionItemController {



    @Autowired
    TransactionItemRepository transactionItemRepository;

    @Autowired
    TransactionItemService transactionItemService;

/*
    Im not sure we need any of these... Transaction-items should only be accessed through a transaction

    @ApiOperation(value = "Allows authenticated user to post a transaction-item to database.")
    @ApiImplicitParam(name = "transaction-item object", dataType = "TransactionItem", paramType = "body",
            value = "transaction-item object")
    @PostMapping(value = "/add")
    public ResponseEntity<?> addNewTransactionItem(@Valid @RequestBody TransactionItem transactionItem) {
        //to we want to associate transaction entries with current User? or just with String personnel?
        transactionItemRepository.save(transactionItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Returns all transaction-items. Pageable.", response = TransactionItem.class, responseContainer = "List")
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
    public ResponseEntity<?> listAllTransactionItems(@PageableDefault(page = 0,
            size = 3) Pageable pageable)
    {
        List<TransactionItem> transactionItems = transactionItemService.findAll(pageable);
        return new ResponseEntity<>(transactionItems, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns transaction-item based on transaction-item id.", response = TransactionItem.class)
    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<?> findTransactionItemById(@PathVariable long id)
    {
        return new ResponseEntity<>(transactionItemService.findTransactionItemById(id), HttpStatus.OK);
    }*/

    // keeping these ones in tact because they may be useful for a good experience

    @ApiOperation(value = "Deletes transaction-item based on transaction-item id.", response = TransactionItem.class)
    @DeleteMapping("/delete/{titemId}")
    public ResponseEntity<?> deleteTransactionItemById(@PathVariable Long titemId)
    {
        transactionItemService.delete(titemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/update/{titemId}")
    public ResponseEntity<?> updateTransactionItem(
            @RequestBody TransactionItem updateTransactionItem,
            @PathVariable long titemId)
    {
        transactionItemService.update(updateTransactionItem, titemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
