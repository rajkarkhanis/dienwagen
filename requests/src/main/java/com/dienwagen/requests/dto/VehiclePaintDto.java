package com.dienwagen.requests.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class VehiclePaintDto {
    @NotNull(message = "Enter Paint ID")
    private Integer paintId;

    @NotNull(message = "Enter valid Paint name")
    private String paintName;

    @NotNull(message = "Enter valid Paint type")
    private String paintType;

    private VehicleModelDto vehicleModel;

    @PositiveOrZero(message = "Paint price cannot be negative")
    private Integer paintPrice;
}
