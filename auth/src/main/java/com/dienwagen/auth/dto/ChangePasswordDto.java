package com.dienwagen.auth.dto;

import lombok.Data;

@Data
public class ChangePasswordDto {
    private String username;
    private String newPassword;
    private String confirmPassword;
}
