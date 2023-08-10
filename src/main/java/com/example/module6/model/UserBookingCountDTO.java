package com.example.module6.model;

public class UserBookingCountDTO {
    private Long bookingCount;
    private Long bookingUserId;
    private Long bookedUserId;

    public UserBookingCountDTO(Long bookingCount, Long bookingUserId, Long bookedUserId) {
        this.bookingCount = bookingCount;
        this.bookingUserId = bookingUserId;
        this.bookedUserId = bookedUserId;
    }

    public Long getBookingCount() {
        return bookingCount;
    }

    public void setBookingCount(Long bookingCount) {
        this.bookingCount = bookingCount;
    }

    public Long getBookingUserId() {
        return bookingUserId;
    }

    public void setBookingUserId(Long bookingUserId) {
        this.bookingUserId = bookingUserId;
    }

    public Long getBookedUserId() {
        return bookedUserId;
    }

    public void setBookedUserId(Long bookedUserId) {
        this.bookedUserId = bookedUserId;
    }
}