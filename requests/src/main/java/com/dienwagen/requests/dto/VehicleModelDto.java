package com.dienwagen.requests.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VehicleModelDto {
    @NotNull(message = "Enter Model ID")
    private Integer modelId;

    @NotNull(message = "Enter valid Model name")
    private String modelName;
}
