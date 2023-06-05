package com.dienwagen.requests.service;

import com.dienwagen.requests.dto.VehicleEquipmentDto;
import com.dienwagen.requests.dto.VehicleModelDto;
import com.dienwagen.requests.entity.VehicleEquipment;
import com.dienwagen.requests.repository.VehicleEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleEquipmentService {
    private final VehicleEquipmentRepository equipmentRepository;
    private final VehicleModelService modelService;
    private final VehicleEngineService engineService;

    @Autowired
    public VehicleEquipmentService(VehicleEquipmentRepository equipmentRepository, VehicleModelService modelService, VehicleEngineService engineService) {
        this.equipmentRepository = equipmentRepository;
        this.modelService = modelService;
        this.engineService = engineService;
    }

    // Return list of equipments for model
    public List<VehicleEquipmentDto> getEquipmentsForModel(VehicleModelDto modelDto) {
        return equipmentRepository.findByVehicleModelId(modelDto.getModelId())
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    // Convert VehicleEquipment entity to DTO
    public VehicleEquipmentDto convertToDto(VehicleEquipment equipment) {
        if (equipment == null) {
            return new VehicleEquipmentDto();
        }
        VehicleEquipmentDto dto = new VehicleEquipmentDto();
        dto.setEquipmentId(equipment.getId());
        dto.setEquipmentName(equipment.getName());
        dto.setEquipmentPrice(equipment.getPrice());
        dto.setVehicleEngine(engineService.convertToDto(equipment.getVehicleEngine()));
        dto.setVehicleModel(modelService.convertToDto(equipment.getVehicleModel()));
        return dto;
    }

    // Convert To Entity
    public VehicleEquipment convertToEntity(VehicleEquipmentDto dto) {
        VehicleEquipment equipment = new VehicleEquipment();
        equipment.setId(dto.getEquipmentId());
        equipment.setName(dto.getEquipmentName());
        equipment.setPrice(dto.getEquipmentPrice());
        equipment.setVehicleModel(modelService.convertToEntity(dto.getVehicleModel()));
        equipment.setVehicleEngine(engineService.convertToEntity(dto.getVehicleEngine()));
        return equipment;
    }
}
