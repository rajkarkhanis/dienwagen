package com.dienwagen.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealerDto {
    private Integer id;
    private String name;
    private String contact;
    private String email;
    private String address;
}
