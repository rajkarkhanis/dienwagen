package com.dienwagen.orders.exception;

import java.util.function.Supplier;

public class ExceptionSupplier {
    public static final Supplier<ResourceNotFoundException> RESOURCE_NOT_FOUND_EXCEPTION = () -> new ResourceNotFoundException("Resource not found");
}
