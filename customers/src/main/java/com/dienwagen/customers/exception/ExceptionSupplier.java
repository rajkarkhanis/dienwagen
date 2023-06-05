package com.dienwagen.customers.exception;

import java.util.function.Supplier;

public class ExceptionSupplier {
    public static final Supplier<RecordNotFoundException> RECORD_NOT_FOUND_EXCEPTION = () -> new RecordNotFoundException("Record Not Found");
}
