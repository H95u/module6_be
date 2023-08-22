package com.example.module6.service.impl;

import com.example.module6.model.Booking;
import com.example.module6.model.DTO.RevenueDTO;
import com.example.module6.model.User;
import com.example.module6.repository.IBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingService {

    @Autowired
    private IBookingRepository iBookingRepository;
    @Autowired
    UserService userService;

    public Booking rentService(Booking booking) {
        return iBookingRepository.save(booking);
    }

    public Booking acceptBooking(Long bookingId) {
        Booking booking = iBookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            booking.setStatus(2);
            return iBookingRepository.save(booking);
        }
        return null;
    }

    public Booking finishBookingUser(Long bookingId) {
        Booking booking = iBookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            booking.setStatus(3);
            return iBookingRepository.save(booking);
        }
        return null;
    }

    public Booking finishBookedUser(Long bookingId) {
        Booking booking = iBookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            User bookedUser = userService.findOne(booking.getBookedUser().getId()).get();
            booking.setStatus(5);
            bookedUser.setMoney(bookedUser.getMoney() + booking.getTotal());
            userService.save(bookedUser);
            return iBookingRepository.save(booking);
        }
        return null;
    }

    public Booking rejectBooking(Long bookingId) {
        Booking booking = iBookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            User bookingUser = userService.findOne(booking.getBookingUser().getId()).get();
            User bookedUser = userService.findOne(booking.getBookedUser().getId()).get();
            bookingUser.setMoney(bookingUser.getMoney() + booking.getTotal());
            bookedUser.setRentCount(bookedUser.getRentCount() - 1);
            userService.save(bookingUser);
            userService.save(bookedUser);
            booking.setStatus(4);
            return iBookingRepository.save(booking);
        }
        return null;
    }

    public List<RevenueDTO> findAllTotalByBookedUserId(Long bookedUserId, Integer year) {
        return iBookingRepository.findAllTotalByBookedUserId(bookedUserId, year);
    }

    public List<Booking> getBookingsByBookedUserId(Long bookedUserId) {
        return iBookingRepository.findByBookedUserId(bookedUserId);
    }

    public List<Booking> getBookingsByBookedUserIdAndAccepted(Long bookedUserId) {
        return iBookingRepository.findByBookedUserIdAndStatus(bookedUserId, 2);
    }

    public List<Booking> getBookingsByBookedUserIdAndWaiting(Long bookedUserId) {
        return iBookingRepository.findByBookedUserIdAndStatus(bookedUserId, 1);
    }

    public List<Booking> getBookingsByBookingUserId(Long bookingUserId) {
        return iBookingRepository.findAllByBookingUser_Id(bookingUserId);
    }

    public Optional<Booking> findById(Long id) {
        return iBookingRepository.findById(id);
    }

}
