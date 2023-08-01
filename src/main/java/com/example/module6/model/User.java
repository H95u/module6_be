package com.example.module6.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String img;
    private Double price;
    private Integer gender;
    private LocalDate dob;
    private Integer status;
    private Double money;
    @ManyToMany
    private List<Options> options;
    @ManyToOne
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
