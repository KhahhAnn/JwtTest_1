package vn.Ka.Spring.JwtDemo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idRole;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
        private ERole roleName;
}
