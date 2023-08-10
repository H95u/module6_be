package com.example.module6.model;

public class UserBookingDTO {
    private User bookingUser;
    private Booking booking;
    private Long bookingCount;
    private User user;
    private Long bookingUserId;
    private Long bookedUserId;

    public UserBookingDTO(Long bookingCount, User user) {
        this.bookingCount = bookingCount;
        this.user = user;
    }

    public UserBookingDTO(User bookingUser, Booking booking) {
        this.bookingUser = bookingUser;
        this.booking = booking;
    }


    public Long getBookingCount() {
        return bookingCount;
    }

    public void setBookingCount(Long bookingCount) {
        this.bookingCount = bookingCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getBookingUser() {
        return bookingUser;
    }

    public void setBookingUser(User bookingUser) {
        this.bookingUser = bookingUser;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}