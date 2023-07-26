package vn.Ka.Spring.JwtDemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.Ka.Spring.JwtDemo.entity.Users;
import vn.Ka.Spring.JwtDemo.repository.UserRepository;
@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.userRepository.findByUserName(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return CustomUserDetail.mapUserToUserDetail(user);
    }
}
