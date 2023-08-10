package com.example.module6.model;

public class UserBookingDTO {
    private User bookingUser;
    private Booking booking;



    public UserBookingDTO(User bookingUser, Booking booking) {
        this.bookingUser = bookingUser;
        this.booking = booking;
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