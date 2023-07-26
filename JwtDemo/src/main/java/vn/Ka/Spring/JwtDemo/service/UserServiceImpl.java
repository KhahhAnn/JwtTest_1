package vn.Ka.Spring.JwtDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import vn.Ka.Spring.JwtDemo.entity.Users;
import vn.Ka.Spring.JwtDemo.repository.UserRepository;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users findByUserName(String userName) {
        return this.userRepository.findByUserName(userName);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return this.existsByUserName(userName);
    }

    @Override
    public boolean existsBtEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public Users saveOrUpdate(Users user) {
        return this.userRepository.saveAndFlush(user);
    }
}
