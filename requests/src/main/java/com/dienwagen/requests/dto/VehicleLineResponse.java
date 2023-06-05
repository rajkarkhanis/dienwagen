package com.dienwagen.requests.dto;

import lombok.Data;

import java.util.List;

@Data
public class VehicleLineResponse {
    private List<VehicleLineDto> lines;
    private List<VehicleBodyDto> bodies;
}
