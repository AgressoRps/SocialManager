package ru.starokozhev.SocialManager.entity;

import lombok.Data;
import ru.starokozhev.SocialManager.enums.vtope.VtopeBotAccess;
import ru.starokozhev.SocialManager.enums.vtope.VtopeBotStatus;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bot")
@Data
public class Bot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private VtopeBotStatus status;

    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "access")
    private VtopeBotAccess access;

    private Boolean earning;

    @Column(name = "allow_control")
    private Boolean allowControl;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_earned")
    private Earned earned;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "bot", fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "bot", fetch = FetchType.LAZY)
    private Set<Proxy> proxies = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "bot", fetch = FetchType.LAZY)
    private Set<Strategy> strategies = new HashSet<>();

}
