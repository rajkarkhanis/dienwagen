package com.dienwagen.requests.controller;

import com.dienwagen.requests.dto.VehicleRequestDto;
import com.dienwagen.requests.dto.VehicleRequestResponse;
import com.dienwagen.requests.entity.VehicleRequest;
import com.dienwagen.requests.exception.ResourceNotFoundException;
import com.dienwagen.requests.service.VehicleRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/requests")
public class VehicleRequestController {

    private final VehicleRequestService service;

    @Autowired
    public VehicleRequestController(VehicleRequestService service) {
        this.service = service;
    }

    // Create a new request
    // Create a new VehicleRequest object and kick-start vehicle config
    @GetMapping(path = "/new")
    public ResponseEntity<Integer> newRequest() {
        return new ResponseEntity<>(service.newRequest().getId(), HttpStatus.CREATED);
    }

    // Open an existing request
    // Get request id and return VehicleRequest object
    @GetMapping(path = "/open/{id}")
    public ResponseEntity<VehicleRequestResponse> openRequest(@PathVariable Integer id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.getExistingRequest(id), HttpStatus.OK);
    }

    // Persist a VehicleRequest
    @PostMapping(path = "/save")
    public ResponseEntity<VehicleRequestResponse> saveRequest(@RequestBody VehicleRequestDto vehicleRequestDto) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.saveRequest(vehicleRequestDto), HttpStatus.OK);
    }

    // Find by id
    @GetMapping(path = "/{id}")
    public ResponseEntity<VehicleRequest> findById(@PathVariable Integer id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.getRequestById(id), HttpStatus.OK);
    }
}
