package com.dienwagen.requests.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VehicleBodyDto {
    @NotNull(message = "Body ID cannot be null")
    private Integer bodyId;

    @NotNull(message = "Enter valid body type")
    private String bodyType;
    private VehicleModelDto vehicleModel;
}
