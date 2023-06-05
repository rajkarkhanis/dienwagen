package com.dienwagen.requests.controller;

import com.dienwagen.requests.dto.VehicleCatalogue;
import com.dienwagen.requests.dto.VehicleFilter;
import com.dienwagen.requests.dto.VehicleLineResponse;
import com.dienwagen.requests.service.VehicleLineResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/models")
public class VehicleModelController {
    private final VehicleLineResponseService responseService;

    @Autowired
    public VehicleModelController(VehicleLineResponseService responseService) {
        this.responseService = responseService;
    }

    // Get list of all models, lines, bodies, engines, and prices
    @GetMapping
    public ResponseEntity<VehicleCatalogue> getCatalogue() {
        return new ResponseEntity<>(responseService.getCatalogue(), HttpStatus.OK);
    }

    // Post VehicleFilter to /models
    // Get VehicleModelResponse based on filters specified in VehicleFilter
    @PostMapping
    public ResponseEntity<VehicleLineResponse> getModelsByFilter(@RequestBody VehicleFilter filter) {
        return new ResponseEntity<>(responseService.getModelsByFilter(filter), HttpStatus.OK);
    }

}
