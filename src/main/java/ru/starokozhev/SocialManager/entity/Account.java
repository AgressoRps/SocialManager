package ru.starokozhev.SocialManager.entity;

import lombok.Data;
import ru.starokozhev.SocialManager.enums.AccountState;
import ru.starokozhev.SocialManager.enums.vtope.VtopeAccountService;
import ru.starokozhev.SocialManager.enums.vtope.VtopeProxyMode;

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
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bot")
    private Bot bot;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "service")
    private VtopeAccountService service;

    private String login;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_strategy")
    private Strategy strategy;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mail")
    private Mail mail;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "proxy_mode")
    private VtopeProxyMode proxyMode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proxy")
    private Proxy proxy;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "account_state")
    private AccountState accountState;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @Column(name = "date_close")
    private LocalDateTime dateClose;

}
