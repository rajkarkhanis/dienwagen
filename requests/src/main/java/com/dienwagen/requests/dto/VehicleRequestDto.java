package com.dienwagen.requests.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class VehicleRequestDto {
    @NotNull(message = "Enter Request ID")
    private Integer requestId;

    private VehicleModelDto vehicleModel;
    private VehicleLineDto vehicleLine;
    private VehicleBodyDto vehicleBody;
    private VehicleEngineDto vehicleEngine;
    private VehicleEquipmentDto vehicleEquipment;
    private VehiclePaintDto interiorPaint;
    private VehiclePaintDto exteriorPaint;

    @Positive(message = "Price cannot be negative or zero")
    private Integer totalPrice;

    @PositiveOrZero(message = "Discount cannot be negative")
    private Integer discount;
}
