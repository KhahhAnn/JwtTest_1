package vn.Ka.Spring.JwtDemo.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.Ka.Spring.JwtDemo.security.CustomUserDetail;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${vn.Ka.Spring.JwtDemo.jwt.secret}")
    private String JWT_SECRET;
    @Value("${vn.Ka.Spring.JwtDemo.jwt.expiration}")
    private int JWT_EXPIRATION;
    // Tạo jwt từ thông tin user
    public String generateToken(CustomUserDetail customUserDetail) {
        Date now = new Date();
        Date dateExpired = new Date(now.getTime() + JWT_EXPIRATION);
        // Tạo chuỗi jwt từ username
        return Jwts.builder()
                .setSubject(customUserDetail.getUsername())
                .setIssuedAt(now)
                .setExpiration(dateExpired)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }
    //Lấy thông tin user từ jwt
    public String getUserNameFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET)
                .parseClaimsJws(token).getBody();
        // trả lại username
        return claims.getSubject();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }
}
