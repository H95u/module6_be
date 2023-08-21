package com.example.module6.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "longtext")
    private String description;
    @ManyToOne
    private User accuser;
    @ManyToOne
    private User accused;
    private LocalDate reportTime = LocalDate.now();
}
