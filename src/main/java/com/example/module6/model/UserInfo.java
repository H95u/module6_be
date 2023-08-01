package com.example.module6.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String img;
    private Double price;
    private Integer gender;
    private String email;
    private LocalDate dob;
    private Integer status;
    private Double money;
    @OneToOne
    private User user;
    @ManyToMany
    private List<Options> options;
    @ManyToOne
    private Address address;
}
