package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.Client;
import com.lambdaschool.tiemendo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping(value="/{clientId}/paymentschedule", produces={"application/json"})
    public ResponseEntity<?> getPaymentSchedule(@PathVariable long clientId) {
        return new ResponseEntity<>(clientService.getPaymentSchedule(clientId), HttpStatus.OK);
    }
    @PostMapping(value="/{clientId}/newschedule", produces={"application/json"}, consumes={"application/json"})
    public ResponseEntity<?> generateNewSchedule(
            @PathVariable long clientId,
            @Valid @RequestBody Client client
    ) {
        return new ResponseEntity<>(clientService.generateNewSchedule(client), HttpStatus.OK);
    }
    @PutMapping(value="/{clientId}/updatePayment", produces={"application/json"}, consumes={"application/json"})
    public ResponseEntity<?> updatePayment(
            @PathVariable long clientId,
            @Valid @RequestBody LocalDate scheduledDate,
            @Valid @RequestBody LocalDate paymentDate
    ) {
        return new ResponseEntity<>(clientService.updatePayment(clientId, scheduledDate, paymentDate), HttpStatus.OK);
    }
    @DeleteMapping(value="/{clientId}/deletePayment", produces={"application/json"})
    public ResponseEntity<?> deletePayment(
            @PathVariable long clientId,
            @Valid @RequestBody LocalDate scheduledDate
    ) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
