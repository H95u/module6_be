package com.example.module6.model;

import java.time.LocalDateTime;

public class UserBookingCountDTO {
    private Long bookedUserId;
    private Long bookingCount;
    private User bookingUser;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public UserBookingCountDTO(Long bookedUserId, Long bookingCount, User bookingUser, LocalDateTime startTime, LocalDateTime endTime) {
        this.bookedUserId = bookedUserId;
        this.bookingCount = bookingCount;
        this.bookingUser = bookingUser;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public UserBookingCountDTO(Long bookedUserId, Long bookingCount, User bookingUser) {
        this.bookedUserId = bookedUserId;
        this.bookingCount = bookingCount;
        this.bookingUser = bookingUser;
    }

    public Long getBookedUserId() {
        return bookedUserId;
    }

    public void setBookedUserId(Long bookedUserId) {
        this.bookedUserId = bookedUserId;
    }

    public Long getBookingCount() {
        return bookingCount;
    }

    public void setBookingCount(Long bookingCount) {
        this.bookingCount = bookingCount;
    }

    public User getBookingUser() {
        return bookingUser;
    }

    public void setBookingUser(User bookingUser) {
        this.bookingUser = bookingUser;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

}