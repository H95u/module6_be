package com.example.module6.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "longtext")
    private String description;
    @ManyToOne
    private User bookedUser;
    @ManyToOne
    private User bookingUser;
}
