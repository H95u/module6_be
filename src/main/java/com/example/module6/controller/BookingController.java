package com.example.module6.controller;

import com.example.module6.model.Booking;
import com.example.module6.model.User;
import com.example.module6.service.impl.BookingService;
import com.example.module6.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/booked/{bookedUserId}")
    public List<Booking> getBookingsByBookedUserId(@PathVariable Long bookedUserId){
        return bookingService.getBookingsByBookedUserId(bookedUserId);
    }

    @PostMapping("/rent")
    public ResponseEntity<Booking> rentService(@RequestBody Booking booking) {
        booking.setStatus(1);
        return new ResponseEntity<>(bookingService.rentService(booking), HttpStatus.ACCEPTED);
    }

    @PutMapping("/accept/{bookingId}")
    public ResponseEntity<Booking> acceptBooking(@PathVariable Long bookingId) {
        return new ResponseEntity<>(bookingService.acceptBooking(bookingId), HttpStatus.ACCEPTED);
    }

    @PutMapping("/reject/{bookingId}")
    public ResponseEntity<Booking> rejectBooking(@PathVariable Long bookingId) {
        return new ResponseEntity<>(bookingService.acceptBooking(bookingId), HttpStatus.ACCEPTED);
    }
}
