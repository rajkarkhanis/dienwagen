package com.dienwagen.requests.service;

import com.dienwagen.requests.dto.VehicleBodyDto;
import com.dienwagen.requests.entity.VehicleBody;
import com.dienwagen.requests.repository.VehicleBodyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VehicleBodyService {
    private final VehicleBodyRepository bodyRepository;
    private final VehicleModelService modelService;

    @Autowired
    public VehicleBodyService(VehicleBodyRepository bodyRepository, VehicleModelService modelService) {
        this.bodyRepository = bodyRepository;
        this.modelService = modelService;
    }

    // Convert VehicleBody entity to DTO
    public VehicleBodyDto convertToDto(VehicleBody body) {
        if (body == null) {
            return new VehicleBodyDto();
        }
        VehicleBodyDto dto = new VehicleBodyDto();
        dto.setBodyId(body.getId());
        dto.setBodyType(body.getType());
        dto.setVehicleModel(modelService.convertToDto(body.getVehicleModel()));
        return dto;
    }

    public VehicleBody convertToEntity(VehicleBodyDto dto) {
        VehicleBody body = new VehicleBody();
        body.setId(dto.getBodyId());
        body.setType(dto.getBodyType());
        body.setVehicleModel(modelService.convertToEntity(dto.getVehicleModel()));
        return body;
    }

    // Get all unique body names
    public Set<String> getAllUniqueBodyNames() {
        return bodyRepository.findAll().stream().map(VehicleBody::getType).collect(Collectors.toSet());
    }

    // Get all bodies by model name
    public List<VehicleBodyDto> getBodiesByModelName(String modelName) {
        return bodyRepository.findByVehicleModelName(modelName)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    // Get all bodies by body type
    public List<VehicleBodyDto> getBodiesByType(String bodyType) {
        return bodyRepository.findByType(bodyType)
                .stream()
                .map(this::convertToDto)
                .toList();
    }
}
