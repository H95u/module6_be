package com.example.module6.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateUserRequest {
    private String nickname;
    private String email;
//    private LocalDate dob;
    private Long addressId;
    private Integer gender;
}
