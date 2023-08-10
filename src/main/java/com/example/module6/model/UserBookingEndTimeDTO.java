package com.example.module6.model;
import java.time.LocalDateTime;

public class UserBookingEndTimeDTO {
    private Long bookedUserId;
    private User bookingUser;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public UserBookingEndTimeDTO(Long bookedUserId, User bookingUser, LocalDateTime startTime, LocalDateTime endTime) {
        this.bookedUserId = bookedUserId;
        this.bookingUser = bookingUser;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getBookedUserId() {
        return bookedUserId;
    }

    public void setBookedUserId(Long bookedUserId) {
        this.bookedUserId = bookedUserId;
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
