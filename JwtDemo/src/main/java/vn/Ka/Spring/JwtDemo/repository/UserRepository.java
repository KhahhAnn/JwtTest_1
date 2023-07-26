package vn.Ka.Spring.JwtDemo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.Ka.Spring.JwtDemo.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
