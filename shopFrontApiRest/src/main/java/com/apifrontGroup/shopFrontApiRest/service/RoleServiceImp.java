package com.apifrontGroup.shopFrontApiRest.service;

import com.apifrontGroup.shopFrontApiRest.entity.Role;
import com.apifrontGroup.shopFrontApiRest.entity.User;
import com.apifrontGroup.shopFrontApiRest.repository.RoleRepository;
import com.apifrontGroup.shopFrontApiRest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long id, Role role) {
        return null;
    }

    @Override
    public void deleteRole(Long id) {

    }
}
