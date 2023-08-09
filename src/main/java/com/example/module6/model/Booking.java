package com.example.module6.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int status;

    @ManyToOne
    private Options option;

    @ManyToOne
    private User bookingUser;

    @ManyToOne
    private User bookedUser;

    private Double total;

}
