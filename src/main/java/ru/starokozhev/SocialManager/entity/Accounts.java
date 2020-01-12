package ru.starokozhev.SocialManager.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Data
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long warning;

    private Long success;

    private Long danger;

    private Long primary;

}
