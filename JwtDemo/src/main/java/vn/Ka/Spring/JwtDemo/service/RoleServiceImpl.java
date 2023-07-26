package vn.Ka.Spring.JwtDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import vn.Ka.Spring.JwtDemo.entity.Roles;
import vn.Ka.Spring.JwtDemo.repository.RoleRepository;

import java.util.Optional;

public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Roles> findByRoleName(String roleName) {
        return this.roleRepository.findByRoleName(roleName);
    }
}
