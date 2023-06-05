package com.dienwagen.requests.controller;

import com.dienwagen.requests.dto.VehicleEquipmentDto;
import com.dienwagen.requests.dto.VehicleModelDto;
import com.dienwagen.requests.service.VehicleEquipmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/equipments")
public class VehicleEquipmentController {
    private final VehicleEquipmentService service;

    @Autowired
    public VehicleEquipmentController(VehicleEquipmentService service) {
        this.service = service;
    }

    // Get All Equipments
    @PostMapping
    public ResponseEntity<List<VehicleEquipmentDto>> getAllEquipments(@RequestBody @Valid VehicleModelDto modelDto) {
        return new ResponseEntity<>(service.getEquipmentsForModel(modelDto), HttpStatus.OK);
    }
}
