package vn.Ka.Spring.JwtDemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idUser;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date created;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "userName"),
            inverseJoinColumns = @JoinColumn(name = "idRole")
    )

    private Set<Roles> listRole = new HashSet<>();


}
