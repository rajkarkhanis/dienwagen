package com.dienwagen.requests.dto;

import lombok.Data;

@Data
public class VehicleRequestResponse {
    private Integer requestId;
    private VehicleModelDto vehicleModel;
    private VehicleLineDto vehicleLine;
    private VehicleEngineDto vehicleEngine;
    private VehicleBodyDto vehicleBody;
    private VehicleEquipmentDto vehicleEquipment;
    private VehiclePaintDto interiorPaint;
    private VehiclePaintDto exteriorPaint;
    private Integer totalPrice;
    private Integer discount;
}
