package ru.starokozhev.SocialManager.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "order_product")
@Data
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_account",
            joinColumns = @JoinColumn(name = "id_order_product"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private Set<Product> products;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderProduct", fetch = FetchType.LAZY)
    private Set<ProxyProduct> proxies;

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

    @Column(name = "date_close")
    private LocalDateTime dateClose;

}
