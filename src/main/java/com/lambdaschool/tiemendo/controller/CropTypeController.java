package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.service.CropTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/croptypes")
public class CropTypeController
{
    @Autowired
    private CropTypeService cropTypeService;
    
    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<?> getAllCropTypes()
    {
        return new ResponseEntity<>(cropTypeService.getAllCropTypes(), HttpStatus.OK);
    }
}
