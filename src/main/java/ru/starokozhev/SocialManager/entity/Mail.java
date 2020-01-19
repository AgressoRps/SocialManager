package ru.starokozhev.SocialManager.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "mail")
@Data
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

}
