package com.dienwagen.auth.controller;

import com.dienwagen.auth.dto.AuthRequest;
import com.dienwagen.auth.dto.ChangePasswordDto;
import com.dienwagen.auth.dto.TokenResponse;
import com.dienwagen.auth.dto.UserDto;
import com.dienwagen.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<TokenResponse> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(userService.authenticateUser(authRequest));
    }

    @PostMapping(path = "/forgot-password")
    public ResponseEntity<TokenResponse> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        return ResponseEntity.ok(userService.changePassword(changePasswordDto));
    }
}
