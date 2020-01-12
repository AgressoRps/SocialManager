package ru.starokozhev.SocialManager.entity;

import lombok.Data;
import ru.starokozhev.SocialManager.enums.BotAccess;
import ru.starokozhev.SocialManager.enums.BotStatus;

import javax.persistence.*;

@Entity
@Table(name = "vtope_bot")
@Data
public class VtopeBot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BotStatus status;

    private String name;

    private BotAccess access;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_earned", referencedColumnName = "id")
    private Earned earned;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_accounts", referencedColumnName = "id")
    private Accounts accounts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_proxies", referencedColumnName = "id")
    private Proxies proxies;

}
