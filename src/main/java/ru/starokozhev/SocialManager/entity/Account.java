package ru.starokozhev.SocialManager.entity;

import lombok.Data;
import ru.starokozhev.SocialManager.enums.AccountName;
import ru.starokozhev.SocialManager.enums.AccountType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private AccountName name;

    @Enumerated(value = EnumType.STRING)
    private AccountType type;

    private Double price;

    private String url;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

}
