package ru.starokozhev.SocialManager.entity;

import lombok.Data;
import ru.starokozhev.SocialManager.enums.ProductName;
import ru.starokozhev.SocialManager.enums.ProductType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ProductName name;

    @Enumerated(value = EnumType.STRING)
    private ProductType type;

    private Double price;

    private String url;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @Column(name = "date_close")
    private LocalDateTime dateClose;

}
