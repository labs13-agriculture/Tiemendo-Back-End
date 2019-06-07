package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.Farmer;

import com.lambdaschool.tiemendo.model.Yield;
import com.lambdaschool.tiemendo.repository.FarmerRepository;
import com.lambdaschool.tiemendo.repository.YieldRepository;
import com.lambdaschool.tiemendo.service.FarmerService;
import com.lambdaschool.tiemendo.service.YieldService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/yield")
public class YieldController {

    @Autowired
    YieldService yieldService;

    @Autowired
    YieldRepository yieldRepository;

    @Autowired
    FarmerService farmerService;

    @Autowired
    FarmerRepository farmerRepository;


    @ApiOperation(value = "Returns all yields.", response = Yield.class)
    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<?> findAll()
    {

        return new ResponseEntity<>(yieldRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Gets yield history by farmer by cropname. Takes farmerid and cropname.", response = Yield.class)
    @GetMapping(value = "/{farmerid}/{cropname}", produces = {"application/json"})
    public ResponseEntity<?> getYieldByFarmerAndCropType(@PathVariable long farmerid,@PathVariable String cropname)
    {

        return new ResponseEntity<>(yieldService.getYieldByFarmerAndCropType(farmerid,cropname), HttpStatus.OK);
    }

    @GetMapping(value = "/{farmerid}", produces = {"application/json"})
    public ResponseEntity<?> getYieldByFarmerId(@PathVariable long farmerid)
    {
        Farmer currentFarmer = farmerService.findFarmer(farmerid);


        return new ResponseEntity<>(currentFarmer.getYieldHistory(), HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes yield record based on yield record id.", response = Yield.class)
    @DeleteMapping("/delete/{yieldid}")
    public ResponseEntity<?> deleteYieldItemById(@PathVariable Long yieldid)
    {
        yieldService.delete(yieldid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Updates yield record based on yield record id.", response = Yield.class)
    @PutMapping(value = "/update/{yieldid}")
    public ResponseEntity<?> updateYieldItem(
            @RequestBody
                    Yield updateYieldItem,
            @PathVariable
                    long yieldid)
    {
        yieldService.update(updateYieldItem, yieldid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Allows authenticated user to post a yield-record to database.")
    @ApiImplicitParam(name = "yield-item object", dataType = "Yield", paramType = "body",
            value = "yield object")
    @PostMapping(value = "/add/{farmerid}")
    public ResponseEntity<?> addNewYieldItem(@PathVariable long farmerid,@Valid @RequestBody Yield yield) {

        Farmer currentFarmer = farmerService.findFarmer(farmerid);
        currentFarmer.getYieldHistory().add(yield);


        farmerRepository.save(currentFarmer);

        return new ResponseEntity<>(HttpStatus.OK);


    }
}
