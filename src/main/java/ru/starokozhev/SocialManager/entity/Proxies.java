package ru.starokozhev.SocialManager.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "proxies")
@Data
public class Proxies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long warning;

    private Long success;

    private Long danger;

}
