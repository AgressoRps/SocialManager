package ru.starokozhev.SocialManager.entity;

import lombok.Data;
import ru.starokozhev.SocialManager.enums.Role;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_app")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(updatable = false)
    private String login;

    @Column(updatable = false)
    private String email;

    private String password;

    @Column(name = "date_register", updatable = false)
    private LocalDateTime dateRegister;

    @Column(name = "date_last_auth")
    private LocalDateTime dateLastAuth;

    @Column(name = "date_last_operation")
    private LocalDateTime dateLastOperation;

    @Column(name = "date_close")
    private LocalDateTime dateClose;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String user;

    private String key;

    private Double balance;

}
