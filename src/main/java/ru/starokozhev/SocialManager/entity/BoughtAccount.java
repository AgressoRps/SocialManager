package ru.starokozhev.SocialManager.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "bought_account")
@Data
public class BoughtAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_account",
            joinColumns = @JoinColumn(name = "id_bought_account"),
            inverseJoinColumns = @JoinColumn(name = "id_account")
    )
    private Set<Account> accounts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boughtAccount", fetch = FetchType.LAZY)
    private Set<ProxyAccount> proxies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "total_price")
    private Double totalPrice;

    private Long count;

    @Column(name = "date_order")
    private LocalDateTime dateOrder;

    @Column(name = "date_payed")
    private LocalDateTime datePayed;

    @Column(name = "is_payed")
    private Boolean isPayed;

}
