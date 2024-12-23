package com.ra.module5_project.service.role;

import com.ra.module5_project.model.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findByName(String roleName);
}
