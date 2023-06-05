package com.dienwagen.requests.service;

import com.dienwagen.requests.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleLineResponseService {
    private final VehicleModelService modelService;
    private final VehicleEngineService engineService;
    private final VehicleBodyService bodyService;
    private final VehicleLineService lineService;

    @Autowired
    public VehicleLineResponseService(VehicleModelService modelService, VehicleEngineService engineService, VehicleBodyService bodyService, VehicleLineService lineService) {
        this.modelService = modelService;
        this.engineService = engineService;
        this.bodyService = bodyService;
        this.lineService = lineService;
    }

    // Return models, lines, bodies, engines, and their prices
    public VehicleCatalogue getCatalogue() {
        VehicleCatalogue catalogue = new VehicleCatalogue();
        catalogue.setModels(modelService.getAllUniqueModelNames());
        catalogue.setEngines(engineService.getAllUniqueEngineNames());
        catalogue.setBodies(bodyService.getAllUniqueBodyNames());
        return catalogue;
    }

    // Return VehicleModelResponse based on VehicleFilter applied
    public VehicleLineResponse getModelsByFilter(VehicleFilter filter) {
        VehicleLineResponse response = new VehicleLineResponse();
        List<VehicleLineDto> lines;
        List<VehicleBodyDto> bodies;

        // if model name is provided
        if (filter.getModelName() != null) {
            lines = lineService.getLinesByModelName(filter.getModelName());
            bodies = bodyService.getBodiesByModelName(filter.getModelName());
            response.setLines(lines);
            response.setBodies(bodies);
            return response;
        }

        // if body type is provided
        if (filter.getBodyType() != null) {
            bodies = bodyService.getBodiesByType(filter.getBodyType());
            lines = lineService.getAllLines()
                    .stream()
                    .filter(line -> line.getLineName().contains(filter.getBodyType()))
                    .toList();
            response.setBodies(bodies);
            response.setLines(lines);
            return response;
        }

        // if engine name is provided
        if (filter.getEngineName() != null) {
            // get lines by engine name
            lines = lineService.getLinesByEngineName(filter.getEngineName());
            bodies = lines.stream()
                    .map(VehicleLineDto::getVehicleModel)
                    .map(model -> bodyService.getBodiesByModelName(model.getModelName()))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toSet())
                    .stream()
                    .toList();
            response.setBodies(bodies);
            response.setLines(lines);
            return response;
        }
        return response;
    }
}