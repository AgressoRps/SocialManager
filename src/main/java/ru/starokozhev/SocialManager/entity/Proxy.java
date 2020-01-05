package ru.starokozhev.SocialManager.entity;

import lombok.Data;
import ru.starokozhev.SocialManager.enums.ProxyHttpType;
import ru.starokozhev.SocialManager.enums.ProxyState;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "proxy_product")
@Data
public class Proxy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    private String port;

    private String login;

    private String password;

    @OneToMany(mappedBy = "proxy")
    private Set<Account> accounts = new LinkedHashSet<>();

    @Enumerated(value = EnumType.STRING)
    @Column(name = "proxy_state")
    private ProxyState proxyState;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "proxy_http_type")
    private ProxyHttpType proxyHttpType;

    @Column(name = "date_last_use")
    private LocalDateTime dateLastUse;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @Column(name = "date_close")
    private LocalDateTime dateClose;

}
