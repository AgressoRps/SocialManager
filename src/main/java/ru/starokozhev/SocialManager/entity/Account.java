package ru.starokozhev.SocialManager.entity;

import lombok.Data;
import ru.starokozhev.SocialManager.enums.AccountState;
import ru.starokozhev.SocialManager.enums.AccountType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proxy")
    private Proxy proxy;

    private String login;

    private String password;

    private String newMail;

    private String passwordFromNewMail;

    private String oldMail;

    private String passwordFromOldMail;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "account_state")
    private AccountState accountState;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @Column(name = "date_blocked")
    private LocalDateTime dateBlocked;

    @Column(name = "date_close")
    private LocalDateTime dateClose;

}
