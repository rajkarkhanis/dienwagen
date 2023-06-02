package com.dienwagen.auth.controller;

import com.dienwagen.auth.dto.RoleDto;
import com.dienwagen.auth.entity.Role;
import com.dienwagen.auth.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/roles")
public class RoleController {
    private final RoleService roleService;
    private final ModelMapper mapper = new ModelMapper();

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Save a new Role
    @PostMapping
    public ResponseEntity<RoleDto> saveRole(@RequestBody RoleDto roleDto) {
        Role savedRole = roleService.saveRole(mapper.map(roleDto, Role.class));
        return ResponseEntity.ok(mapper.map(savedRole, RoleDto.class));
    }
}
