package vn.Ka.Spring.JwtDemo.service;

import org.springframework.stereotype.Service;
import vn.Ka.Spring.JwtDemo.entity.Users;

@Service
public interface UserService {
    Users findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsBtEmail(String email);
    Users saveOrUpdate(Users users);
}
