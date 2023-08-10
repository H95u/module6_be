package com.example.module6.controller;

import com.example.module6.model.Booking;
import com.example.module6.service.impl.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/booked/{bookedUserId}")
    public List<Booking> getBookingsByBookedUserId(@PathVariable Long bookedUserId) {
        return bookingService.getBookingsByBookedUserId(bookedUserId);
    }

    @GetMapping("/booking-users/{bookingUserId}")
    public List<Booking> getBookingsByBookingUserId(@PathVariable Long bookingUserId) {
        return bookingService.getBookingsByBookingUserId(bookingUserId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> findOne(@PathVariable Long id) {
        Optional<Booking> bookingOptional = bookingService.findById(id);
        if (bookingOptional.isPresent()) {
            return new ResponseEntity<>(bookingOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/rent")
    public ResponseEntity<?> rentService(@RequestBody Booking booking) {
        if (booking.getStartTime().isAfter(booking.getEndTime())) {
            return new ResponseEntity<>(1, HttpStatus.BAD_REQUEST);
        }

        if (booking.getStartTime().isBefore(LocalDateTime.now())) {
            return new ResponseEntity<>(2, HttpStatus.BAD_REQUEST);
        }

        Booking overlappingBookings = getOverlappingBookings(booking.getStartTime(), booking.getEndTime(), booking.getBookedUser().getId());
        if (overlappingBookings != null) {
            return new ResponseEntity<>(overlappingBookings, HttpStatus.NOT_ACCEPTABLE);
        }
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

    private Booking getOverlappingBookings(LocalDateTime newStartTime, LocalDateTime newEndTime, Long bookedId) {
        Booking overlappingBookings = null;
        List<Booking> existingBookings = bookingService.getBookingsByBookedUserId(bookedId);

        for (Booking existingBooking : existingBookings) {
            LocalDateTime existingStartTime = existingBooking.getStartTime();
            LocalDateTime existingEndTime = existingBooking.getEndTime();

            if ((newStartTime.isAfter(existingStartTime) && newStartTime.isBefore(existingEndTime)) ||
                    (newEndTime.isAfter(existingStartTime) && newEndTime.isBefore(existingEndTime)) ||
                    (newStartTime.isBefore(existingStartTime) && newEndTime.isAfter(existingEndTime))) {
                overlappingBookings = existingBooking;
                break;
            }
        }
        return overlappingBookings;
    }

}
