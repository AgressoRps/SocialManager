package ru.starokozhev.SocialManager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.OnlyId.class)
    private Long id;

    @JsonView(Views.AccessList.class)
    private String text;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yy HH:mm")
    @JsonView(Views.AccessList.class)
    private LocalDateTime creationDate = LocalDateTime.now();

}
