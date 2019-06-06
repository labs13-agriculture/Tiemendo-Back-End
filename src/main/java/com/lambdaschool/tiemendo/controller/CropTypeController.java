package com.lambdaschool.tiemendo.controller;

import com.lambdaschool.tiemendo.model.CropType;
import com.lambdaschool.tiemendo.service.CropTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    
    @GetMapping(value = "/crop/{id}", produces = {"application/json"})
    public ResponseEntity<?> getCropTypeById(@PathVariable long id)
    {
        return new ResponseEntity<>(cropTypeService.getCropTypeById(id), HttpStatus.OK);
    }
    
    @PostMapping(value = "/new", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewCropType(@RequestBody CropType newCrop)
    {
        return new ResponseEntity<>(cropTypeService.save(newCrop), HttpStatus.OK);
    }
    
    @PutMapping(value = "/update/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updateCropType(@PathVariable long id, @RequestBody CropType update)
    {
        return new ResponseEntity<>();
    }
}
