package com.ra.module5_project.service.role;

import com.ra.module5_project.model.entity.Role;
import com.ra.module5_project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository ;
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findRoleByRoleName(roleName);
    }
}
