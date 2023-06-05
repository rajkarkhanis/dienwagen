package com.dienwagen.requests.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class VehicleLineDto {
    @NotNull(message = "Enter Line ID")
    private Integer lineId;

    @NotNull(message = "Enter valid Line name")
    private String lineName;

    private VehicleModelDto vehicleModel;
    private VehicleEngineDto vehicleEngine;

    @PositiveOrZero(message = "Line price cannot be negative")
    private Integer linePrice;
}
