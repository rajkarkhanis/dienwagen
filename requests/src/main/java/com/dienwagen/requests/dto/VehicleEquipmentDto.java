package com.dienwagen.requests.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class VehicleEquipmentDto {
    @NotNull(message = "Enter Equipment ID")
    private Integer equipmentId;

    @NotNull(message = "Enter valid Equipment name")
    private String equipmentName;

    private VehicleModelDto vehicleModel;
    private VehicleEngineDto vehicleEngine;

    @PositiveOrZero(message = "Equipment price cannot be negative")
    private Integer equipmentPrice;
}
