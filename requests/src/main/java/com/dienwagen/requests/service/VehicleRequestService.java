package com.dienwagen.requests.service;

import com.dienwagen.requests.dto.VehicleRequestDto;
import com.dienwagen.requests.dto.VehicleRequestResponse;
import com.dienwagen.requests.entity.VehicleRequest;
import com.dienwagen.requests.exception.ExceptionSupplier;
import com.dienwagen.requests.exception.ResourceNotFoundException;
import com.dienwagen.requests.repository.VehicleRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class VehicleRequestService {
    private final VehicleRequestRepository requestRepository;
    private final VehicleModelService modelService;
    private final VehicleLineService lineService;
    private final VehicleBodyService bodyService;
    private final VehicleEngineService engineService;
    private final VehiclePaintService paintService;
    private final VehicleEquipmentService equipmentService;
    private final Supplier<ResourceNotFoundException> exception = () -> new ResourceNotFoundException("Resource not found");

    @Autowired
    public VehicleRequestService(VehicleRequestRepository requestRepository, VehicleModelService modelService, VehicleLineService lineService, VehicleBodyService bodyService, VehicleEngineService engineService, VehiclePaintService paintService, VehicleEquipmentService equipmentService) {
        this.requestRepository = requestRepository;
        this.modelService = modelService;
        this.lineService = lineService;
        this.bodyService = bodyService;
        this.engineService = engineService;
        this.paintService = paintService;
        this.equipmentService = equipmentService;
    }

    // Create a new request
    public VehicleRequest newRequest() {
        return requestRepository.save(new VehicleRequest());
    }

    // Return an existing VehicleRequest entity
    public VehicleRequest getRequestById(Integer id) throws ResourceNotFoundException {
        return requestRepository.findById(id).orElseThrow(exception);
    }

    // Return an existing VehicleRequest DTO
    public VehicleRequestResponse getExistingRequest(Integer id) throws ResourceNotFoundException {
        VehicleRequest foundRequest = requestRepository.findById(id).orElseThrow(ExceptionSupplier.RESOURCE_NOT_FOUND);
        return convertToDto(foundRequest);
    }

    // Convert VehicleRequest entity to DTO
    public VehicleRequestResponse convertToDto(VehicleRequest request) {
        VehicleRequestResponse response = new VehicleRequestResponse();
        response.setRequestId(request.getId());
        response.setVehicleModel(modelService.convertToDto(request.getVehicleModel()));
        response.setVehicleLine(lineService.convertToDto(request.getVehicleLine()));
        response.setVehicleEngine(engineService.convertToDto(request.getVehicleEngine()));
        response.setVehicleBody(bodyService.convertToDto(request.getVehicleBody()));
        response.setVehicleEquipment(equipmentService.convertToDto(request.getVehicleEquipment()));
        response.setInteriorPaint(paintService.convertToDto(request.getInteriorPaint()));
        response.setExteriorPaint(paintService.convertToDto(request.getExteriorPaint()));
        response.setTotalPrice(request.getTotalPrice());
        response.setDiscount(request.getDiscount());
        return response;
    }

    // Save vehicle request
    public VehicleRequestResponse saveRequest(VehicleRequestDto vehicleRequestDto) throws ResourceNotFoundException {
        VehicleRequest request = convertToEntity(vehicleRequestDto);
        return convertToDto(requestRepository.save(request));
    }

    public VehicleRequest convertToEntity(VehicleRequestDto vehicleRequestDto) throws ResourceNotFoundException {
        VehicleRequest request = getRequestById(vehicleRequestDto.getRequestId());
        request.setVehicleModel(modelService.convertToEntity(vehicleRequestDto.getVehicleModel()));
        request.setVehicleLine(lineService.convertToEntity(vehicleRequestDto.getVehicleLine()));
        request.setVehicleBody(bodyService.convertToEntity(vehicleRequestDto.getVehicleBody()));
        request.setVehicleEngine(engineService.convertToEntity(vehicleRequestDto.getVehicleEngine()));
        request.setInteriorPaint(paintService.convertToEntity(vehicleRequestDto.getInteriorPaint()));
        request.setExteriorPaint(paintService.convertToEntity(vehicleRequestDto.getExteriorPaint()));
        request.setVehicleEquipment(equipmentService.convertToEntity(vehicleRequestDto.getVehicleEquipment()));
        request.setTotalPrice(vehicleRequestDto.getTotalPrice());
        request.setDiscount(vehicleRequestDto.getDiscount());
        return request;
    }
}
