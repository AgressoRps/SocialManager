package ru.starokozhev.SocialManager.entity;

import lombok.Data;
import ru.starokozhev.SocialManager.enums.ProxyHttpType;
import ru.starokozhev.SocialManager.enums.ProxyState;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "proxy_product")
@Data
public class ProxyProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private String login;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order_account")
    private OrderProduct orderProduct;

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
