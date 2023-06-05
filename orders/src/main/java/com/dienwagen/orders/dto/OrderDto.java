package com.dienwagen.orders.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * A DTO for the {@link com.dienwagen.orders.entity.Order} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private VehicleTransactionDto vehicleTransaction;

    @NotNull(message = "Order date cannot be null")
    @FutureOrPresent(message = "Order date cannot be in the past")
    private LocalDate orderDate;

    @NotNull(message = "Estimated delivery date cannot be null")
    @Future(message = "Estimated delivery date cannot be in the past")
    private LocalDate estDeliveryDate;

    private LocalDate actualDeliveryDate;

    @NotEmpty(message = "Order status must be provided")
    private String orderStatus;

    @NotNull(message = "Transport cost cannot be null")
    @PositiveOrZero(message = "Transport cost cannot be negative")
    private Integer transportCost;

    @NotNull(message = "Total price cannot be null")
    @PositiveOrZero(message = "Total price cannot be negative")
    private Integer totalPrice;
}