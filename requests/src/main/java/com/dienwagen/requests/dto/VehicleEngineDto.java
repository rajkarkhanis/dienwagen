package com.dienwagen.requests.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class VehicleEngineDto {
    @NotNull(message = "Enter Engine ID")
    private Integer engineId;

    @NotNull(message = "Enter valid Engine name")
    private String engineName;
    private VehicleModelDto vehicleModel;

    @PositiveOrZero(message = "Price cannot be negative")
    private Integer enginePrice;
}
