package ru.starokozhev.SocialManager.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "earned")
@Data
public class Earned {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long week;

    private Long day;

    private Long month;

}
