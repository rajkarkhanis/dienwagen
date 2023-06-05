package com.dienwagen.requests.service;

import com.dienwagen.requests.dto.VehicleModelDto;
import com.dienwagen.requests.dto.VehiclePaintDto;
import com.dienwagen.requests.entity.VehiclePaint;
import com.dienwagen.requests.repository.VehiclePaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiclePaintService {
    private final VehiclePaintRepository repository;
    private final VehicleModelService modelService;

    @Autowired
    public VehiclePaintService(VehiclePaintRepository repository, VehicleModelService modelService) {
        this.repository = repository;
        this.modelService = modelService;
    }

    // Return list of paints for selected model
    public List<VehiclePaintDto> getPaintsForModel(VehicleModelDto modelDto) {
        return repository.findByVehicleModelId(modelDto.getModelId())
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    // Convert to Dto
    public VehiclePaintDto convertToDto(VehiclePaint paint) {
        if (paint == null) {
            return new VehiclePaintDto();
        }
        VehiclePaintDto dto = new VehiclePaintDto();
        dto.setPaintId(paint.getId());
        dto.setPaintName(paint.getName());
        dto.setPaintType(paint.getType());
        dto.setPaintPrice(paint.getPrice());
        dto.setVehicleModel(modelService.convertToDto(paint.getVehicleModel()));
        return dto;
    }

    // Convert to Entity
    public VehiclePaint convertToEntity(VehiclePaintDto dto) {
        VehiclePaint paint = new VehiclePaint();
        paint.setId(dto.getPaintId());
        paint.setName(dto.getPaintName());
        paint.setType(dto.getPaintType());
        paint.setPrice(dto.getPaintPrice());
        paint.setVehicleModel(modelService.convertToEntity(dto.getVehicleModel()));
        return paint;
    }
}
