package vn.Ka.Spring.JwtDemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import vn.Ka.Spring.JwtDemo.Jwt.JwtAuthenticationFilter;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfiguration {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder());
    }

    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                configurer -> configurer
                        .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("MODERATOR", "ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/api/students").hasAnyRole("MANAGER", "ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/students").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasAnyRole("ADMIN")
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
