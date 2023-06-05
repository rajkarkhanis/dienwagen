package com.dienwagen.customers.dto;

import lombok.Data;

@Data
public class CustomerSearchDto {
    private Integer customerId;
    private String firstName;
    private String lastName;
}
