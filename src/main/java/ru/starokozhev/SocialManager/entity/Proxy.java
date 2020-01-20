package ru.starokozhev.SocialManager.entity;

import lombok.Data;
import ru.starokozhev.SocialManager.enums.ProxyState;
import ru.starokozhev.SocialManager.enums.ProxyType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "proxy")
@Data
public class Proxy {

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
    @Column(name = "type")
    private ProxyType type;

    private String ip;

    private String port;

    private String login;

    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "proxyState")
    private ProxyState proxyState;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @Column(name = "date_close")
    private LocalDateTime dateClose;

}
