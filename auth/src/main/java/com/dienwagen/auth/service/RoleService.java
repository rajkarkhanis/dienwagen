package com.dienwagen.auth.service;

import com.dienwagen.auth.entity.Role;
import com.dienwagen.auth.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Save a new Role
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    // Get Role by name
    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName).orElseThrow();
    }
}
