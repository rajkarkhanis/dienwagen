package com.dienwagen.requests.dto;

import lombok.Data;

import java.util.Set;

@Data
public class VehicleCatalogue {
    private Set<String> models;
    private Set<String> bodies;
    private Set<String> engines;
}
