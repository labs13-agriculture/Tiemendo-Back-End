package com.lambdaschool.tiemendo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @GetMapping(value="/{clientId}/paymentschedule", produces={"application/json"})
    public ResponseEntity<?> getPaymentSchedule(@PathVariable long clientId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(value="/{cleintId}/newschedule", produces={"application/json"}, consumes={"application/json"})
    public ResponseEntity<?> generateNewSchedule(@PathVariable long clientId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping(value="/{clientId}/updatePayment", produces={"application/json"}, consumes={"application/json"})
    public ResponseEntity<?> updatePayment(@PathVariable long clientId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value="/{clientId}/deletePayment", produces={"application/json"})
    public ResponseEntity<?> deletePayment(@PathVariable long clientId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
