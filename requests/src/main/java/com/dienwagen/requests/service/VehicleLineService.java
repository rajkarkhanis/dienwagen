package com.dienwagen.requests.service;

import com.dienwagen.requests.dto.VehicleLineDto;
import com.dienwagen.requests.entity.VehicleLine;
import com.dienwagen.requests.repository.VehicleLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleLineService {
    private final VehicleLineRepository lineRepository;
    private final VehicleModelService modelService;
    private final VehicleEngineService engineService;

    @Autowired
    public VehicleLineService(VehicleLineRepository repository, VehicleModelService modelService, VehicleEngineService engineService) {
        this.lineRepository = repository;
        this.modelService = modelService;
        this.engineService = engineService;
    }

    // Convert VehicleLine entity to DTO
    public VehicleLineDto convertToDto(VehicleLine line) {
        if (line == null) {
            return new VehicleLineDto();
        }
        VehicleLineDto dto = new VehicleLineDto();
        dto.setLineId(line.getId());
        dto.setLineName(line.getName());
        dto.setLinePrice(line.getPrice());
        dto.setVehicleModel(modelService.convertToDto(line.getVehicleModel()));
        dto.setVehicleEngine(engineService.convertToDto(line.getVehicleEngine()));
        return dto;
    }

    public VehicleLine convertToEntity(VehicleLineDto dto) {
        VehicleLine line = new VehicleLine();
        line.setId(dto.getLineId());
        line.setName(dto.getLineName());
        line.setPrice(dto.getLinePrice());
        line.setVehicleModel(modelService.convertToEntity(dto.getVehicleModel()));
        line.setVehicleEngine(engineService.convertToEntity(dto.getVehicleEngine()));
        return line;
    }

    // Get lines by model name
    public List<VehicleLineDto> getLinesByModelName(String modelName) {
        return lineRepository.findByVehicleModelName(modelName)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    // Get lines by engine name
    public List<VehicleLineDto> getLinesByEngineName(String engineName) {
        return lineRepository.findByVehicleEngineName(engineName)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    // Get all lines
    public List<VehicleLineDto> getAllLines() {
        return lineRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }
}
