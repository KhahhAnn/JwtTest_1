package vn.Ka.Spring.JwtDemo.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.Ka.Spring.JwtDemo.entity.Users;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CustomUserDetail implements UserDetails {
    private int idUser;
    private String userName;
    @JsonIgnore
    private String password;
    private String email;
    private String phone;
    private boolean status;
    private Collection<? extends  GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    //Từ thông tin user chuyển sang thông tin CustomUserDetail

    public static CustomUserDetail mapUserToUserDetail(Users user) {
        // Lấy các quyền từ user
        List<GrantedAuthority> listAuthorities = user.getListRole() .stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getRoleName().name()))
                .collect(Collectors.toList());
        //Trả về customeruserdetail
        return new CustomUserDetail(
                user.getIdUser(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.isStatus(),
                listAuthorities
        );
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
