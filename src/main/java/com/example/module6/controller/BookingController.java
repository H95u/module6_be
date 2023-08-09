package com.example.module6.controller;

import com.example.module6.model.Booking;
import com.example.module6.model.User;
import com.example.module6.service.impl.BookingService;
import com.example.module6.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @GetMapping("/booked/{bookedUserId}")
    public List<Booking> getBookingsByBookedUserId(@PathVariable Long bookedUserId){
        return bookingService.getBookingsByBookedUserId(bookedUserId);
    }

    @PostMapping("/rent")
    public Booking rentService(@RequestBody Booking booking) {
        User bookedUser = userService.findOne(booking.getBookedUser().getId()).orElse(null);
        if (bookedUser != null) {
            booking.setBookingUser(userService.findOne(booking.getBookingUser().getId()).orElse(null));
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
