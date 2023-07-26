package vn.Ka.Spring.JwtDemo.service;

import org.springframework.stereotype.Service;
import vn.Ka.Spring.JwtDemo.entity.Roles;

import java.util.Optional;

@Service
public interface RoleService {
    Optional<Roles> findByRoleName(String roleName);
}
