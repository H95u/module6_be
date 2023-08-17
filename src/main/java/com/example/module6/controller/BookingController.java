package com.example.module6.controller;

import com.example.module6.model.Booking;
import com.example.module6.model.DTO.RevenueDTO;
import com.example.module6.model.User;
import com.example.module6.service.impl.BookingService;
import com.example.module6.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserService userService;

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
        return bookingSuccess(booking);
    }

    private ResponseEntity<Booking> bookingSuccess(Booking booking) {
        User bookingUser = userService.findOne(booking.getBookingUser().getId()).get();
        User bookedUser = userService.findOne(booking.getBookedUser().getId()).get();
        bookingUser.setMoney(bookingUser.getMoney() - booking.getTotal());
        bookedUser.setRentCount(bookedUser.getRentCount() + 1);
        userService.save(bookingUser);
        userService.save(bookedUser);
        booking.setStatus(1);
        return new ResponseEntity<>(bookingService.rentService(booking), HttpStatus.ACCEPTED);
    }


    @PutMapping("/accept/{bookingId}")
    public ResponseEntity<Booking> acceptBooking(@PathVariable Long bookingId) {
        return new ResponseEntity<>(bookingService.acceptBooking(bookingId), HttpStatus.ACCEPTED);
    }
    @PutMapping("/{bookingId}/finish-user")
    public ResponseEntity<Booking> finishBookingUser(@PathVariable Long bookingId) {
        return new ResponseEntity<>(bookingService.finishBookingUser(bookingId), HttpStatus.ACCEPTED);
    }
    @PutMapping("/{bookingId}/finish-partner")
    public ResponseEntity<Booking> finishBookedUser(@PathVariable Long bookingId) {
        return new ResponseEntity<>(bookingService.finishBookedUser(bookingId), HttpStatus.ACCEPTED);
    }

    @PutMapping("/reject/{bookingId}")
    public ResponseEntity<Booking> rejectBooking(@PathVariable Long bookingId) {
        return new ResponseEntity<>(bookingService.rejectBooking(bookingId), HttpStatus.ACCEPTED);
    }

    private Booking getOverlappingBookings(LocalDateTime newStartTime, LocalDateTime newEndTime, Long bookedId) {
        Booking overlappingBookings = null;
        List<Booking> existingBookings = bookingService.getBookingsByBookedUserIdAndAccepted(bookedId);

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

    @PostMapping("/update-statusBooking/{id}")
    public ResponseEntity<?> updateStatusByBookingId(@PathVariable Long id,
                                                     @RequestParam Integer status) {
        Optional<Booking> bookingOptional = bookingService.findById(id);
        if (!bookingOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            bookingOptional.get().setStatus(status);
            bookingService.rentService(bookingOptional.get());
            return new ResponseEntity<>(bookingOptional.get(), HttpStatus.ACCEPTED);
        }
    }
    @GetMapping("/revenue/{bookedUserId}")
    public ResponseEntity<?> findAllTotalByBookedUserId(@PathVariable Long bookedUserId, @RequestParam Integer year) {
        List<RevenueDTO> revenueDTOList = bookingService.findAllTotalByBookedUserId(bookedUserId, year);
        if (revenueDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            Map<Integer, Integer> totalMoneyMap = new HashMap<>();
            for (RevenueDTO revenueList : revenueDTOList) {
                totalMoneyMap.put(revenueList.getMonth(), revenueList.getTotal());
            }

            List<Integer> moneyList = new ArrayList<>();
            for (int i = 1; i <= 12; i++) {
                moneyList.add(totalMoneyMap.getOrDefault(i, 0));
            }
            return new ResponseEntity<>(moneyList, HttpStatus.OK);
        }
    }
}
