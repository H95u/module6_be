package com.example.module6.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String img;
    @ManyToOne
    private User user;
}
