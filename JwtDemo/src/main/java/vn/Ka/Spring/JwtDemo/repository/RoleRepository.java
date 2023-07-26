package vn.Ka.Spring.JwtDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.Ka.Spring.JwtDemo.entity.Roles;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByRoleName(String roleName);
}
