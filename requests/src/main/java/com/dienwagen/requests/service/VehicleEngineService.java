package com.dienwagen.requests.service;

import com.dienwagen.requests.dto.VehicleEngineDto;
import com.dienwagen.requests.entity.VehicleEngine;
import com.dienwagen.requests.repository.VehicleEngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VehicleEngineService {
    private final VehicleEngineRepository engineRepository;
    private final VehicleModelService modelService;

    @Autowired
    public VehicleEngineService(VehicleEngineRepository engineRepository, VehicleModelService modelService) {
        this.engineRepository = engineRepository;
        this.modelService = modelService;
    }

    // Convert VehicleEngine entity to DTO
    public VehicleEngineDto convertToDto(VehicleEngine engine) {
        if (engine == null) {
            return new VehicleEngineDto();
        }
        VehicleEngineDto dto = new VehicleEngineDto();
        dto.setEngineId(engine.getId());
        dto.setEngineName(engine.getName());
        dto.setEnginePrice(engine.getPrice());
        dto.setVehicleModel(modelService.convertToDto(engine.getVehicleModel()));
        return dto;
    }

    // Convert VehicleEngine DTO to Entity
    public VehicleEngine convertToEntity(VehicleEngineDto dto) {
        VehicleEngine engine = new VehicleEngine();
        engine.setId(dto.getEngineId());
        engine.setName(dto.getEngineName());
        engine.setPrice(dto.getEnginePrice());
        engine.setVehicleModel(modelService.convertToEntity(dto.getVehicleModel()));
        return engine;
    }

    // Get all unique engine names
    public Set<String> getAllUniqueEngineNames() {
        return engineRepository.findAll()
                .stream()
                .map(VehicleEngine::getName)
                .collect(Collectors.toSet());
    }
}
