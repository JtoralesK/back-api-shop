package com.apifrontGroup.shopFrontApiRest.service;

import com.apifrontGroup.shopFrontApiRest.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRoles();
    Role saveRole(Role role);
    Role updateRole(Long id,Role role);
    void deleteRole(Long id);
}
