package com.example.module6.controller;

import com.example.module6.model.Booking;
import com.example.module6.model.User;
import com.example.module6.service.impl.BookingService;
import com.example.module6.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @PostMapping("/rent/{id}")
    public Booking rentService(@PathVariable Long id, @RequestBody Booking booking) {
        User bookingUser = userService.findOne(id).orElse(null);
        User bookedUser = userService.findOne(booking.getBookedUser().getId()).orElse(null);

        if (bookingUser != null && bookedUser != null) {
            booking.setBookingUser(bookingUser);
            booking.setBookedUser(bookedUser);
            booking.setStatus(1);
            return bookingService.rentService(booking);
        }
        return null;
    }


    @PutMapping("/accept/{bookingId}")
    public Booking acceptBooking(@PathVariable Long bookingId) {
        return bookingService.acceptBooking(bookingId);
    }

    @PutMapping("/reject/{bookingId}")
    public Booking rejectBooking(@PathVariable Long bookingId) {
        return bookingService.rejectBooking(bookingId);
    }
}
