package com.example.module6.request;

import lombok.Data;

@Data
public class CreateReportRequest {
    private String description;
    private Long bookedUserId;
    private Long bookingUserId;
}
