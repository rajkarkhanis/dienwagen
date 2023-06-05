package com.dienwagen.requests.exception;

import java.util.function.Supplier;

public class ExceptionSupplier {
    public static final Supplier<ResourceNotFoundException> RESOURCE_NOT_FOUND = () -> new ResourceNotFoundException("Resource not found");
}
