package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;


public interface RoleService {
    void createRole(Role role);
    Role findRoleById(Long Id);
    Role updateRole(Role role);
    void deleteRoleById(Long Id);
    List<Role> findAllRoles();
}
