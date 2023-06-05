package com.dienwagen.requests.service;

import com.dienwagen.requests.dto.VehicleModelDto;
import com.dienwagen.requests.entity.VehicleModel;
import com.dienwagen.requests.repository.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VehicleModelService {
    private final VehicleModelRepository modelRepository;

    @Autowired
    public VehicleModelService(VehicleModelRepository repository) {
        this.modelRepository = repository;
    }

    // Convert VehicleModel entity to DTO
    public VehicleModelDto convertToDto(VehicleModel model) {
        if (model == null) {
            return new VehicleModelDto();
        }
        VehicleModelDto dto = new VehicleModelDto();
        dto.setModelId(model.getId());
        dto.setModelName(model.getName());
        return dto;
    }

    // Convert VehicleModel DTO to Entity
    public VehicleModel convertToEntity(VehicleModelDto dto) {
        VehicleModel model = new VehicleModel();
        model.setId(dto.getModelId());
        model.setName(dto.getModelName());
        return model;
    }

    // Get all unique model names
    public Set<String> getAllUniqueModelNames() {
        return modelRepository.findAll().stream().map(VehicleModel::getName).collect(Collectors.toSet());
    }
}
