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

    @Column(name = "vtope_key")
    private String vtopeKey;

    @Column(name = "vtope_user")
    private String vtopeUser;

    @Column(name = "telegram_chad_id")
    private Long telegramChatId;

    @Column(name = "notify_changes_in_bot")
    private Boolean notifyChangesInBot;

    @Column(name = "update_from_vtope")
    private Boolean updateFromVtope;

    private Double balance;

}
