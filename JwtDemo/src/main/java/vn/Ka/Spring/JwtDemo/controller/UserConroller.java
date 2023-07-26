package vn.Ka.Spring.JwtDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.Ka.Spring.JwtDemo.Jwt.JwtTokenProvider;
import vn.Ka.Spring.JwtDemo.entity.ERole;
import vn.Ka.Spring.JwtDemo.entity.Roles;
import vn.Ka.Spring.JwtDemo.entity.Users;
import vn.Ka.Spring.JwtDemo.payload.request.SignupRequest;
import vn.Ka.Spring.JwtDemo.payload.response.MessageResponse;
import vn.Ka.Spring.JwtDemo.service.RoleService;
import vn.Ka.Spring.JwtDemo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/api/users")
public class UserConroller {
    @Autowired
     private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        if(userService.existsByUserName(signupRequest.getUserName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: UserName is already"));
        }
        if(userService.existsBtEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already"));
        }
        Users user = new Users();
        user.setUserName(signupRequest.getUserName());
        user.setPassword(signupRequest.getPassword());
        user.setEmail(signupRequest.getEmail());
        user.setPhoneNumber(signupRequest.getPhone());
        Set<String> strRoles = signupRequest.getListRole();
        Set<Roles> listRoles= new HashSet<>();
        if(strRoles == null) {
            Roles userRole = roleService.findByRoleName(ERole.ROLE_USER.toString()).orElseThrow(() -> new RuntimeException(("Error: Role is not found")));
            listRoles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch ((role)) {
                    case "admin":
                        Roles adminRole = roleService.findByRoleName(ERole.ROLE_ADMIN.toString())
                                .orElseThrow(() -> new RuntimeException(("Error: Role is not found")));
                        listRoles.add(adminRole);
                    case "moderator":
                        Roles moderatorRole = roleService.findByRoleName(ERole.ROLE_MODERATOR.toString())
                                .orElseThrow(() -> new RuntimeException(("Error: Role is not found")));
                        listRoles.add(moderatorRole);
                    case "user":
                        Roles userRole = roleService.findByRoleName(ERole.ROLE_USER.toString())
                                .orElseThrow(() -> new RuntimeException(("Error: Role is not found")));
                        listRoles.add(userRole);
                }
            });
        }
        user.setListRole(listRoles);
        userService.saveOrUpdate(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }
}
