package ru.starokozhev.SocialManager.entity;

import lombok.Data;
import ru.starokozhev.SocialManager.enums.vtope.VtopeAccountService;

import javax.persistence.*;

@Entity
@Table(name = "strategy")
@Data
public class Strategy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bot")
    private Bot bot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    private String name;

}
