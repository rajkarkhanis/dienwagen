package com.dienwagen.requests.controller;

import com.dienwagen.requests.dto.VehicleModelDto;
import com.dienwagen.requests.dto.VehiclePaintDto;
import com.dienwagen.requests.service.VehiclePaintService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/paints")
public class VehiclePaintController {
    private final VehiclePaintService service;

    @Autowired
    public VehiclePaintController(VehiclePaintService service) {
        this.service = service;
    }

    // Get all paints
    @PostMapping
    public ResponseEntity<List<VehiclePaintDto>> getAllPaints(@RequestBody @Valid VehicleModelDto modelDto) {
        return new ResponseEntity<>(service.getPaintsForModel(modelDto), HttpStatus.OK);
    }
}
