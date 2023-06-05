package com.dienwagen.requests.dto;

import lombok.Data;

@Data
public class VehicleFilter {
    private String modelName;
    private String engineName;
    private String bodyType;
}
