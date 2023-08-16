package com.example.module6.service.impl;

import com.example.module6.model.Booking;
import com.example.module6.model.DTO.RevenueDTO;
import com.example.module6.model.User;
import com.example.module6.repository.IBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private IBookingRepository iBookingRepository;
    @Autowired UserService userService;

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
            User bookedUser = userService.findOne(booking.getBookingUser().getId()).get();
            bookingUser.setMoney(bookingUser.getMoney() + booking.getTotal());
            bookedUser.setRentCount(bookedUser.getRentCount() - 1);
            userService.save(bookingUser);
            booking.setStatus(4);
            return iBookingRepository.save(booking);
        }
        return null;
    }
    public List<RevenueDTO> findAllTotalByBookedUserId(Long bookedUserId, Integer idYear) {
        List<RevenueDTO> revenueDTOList = iBookingRepository.findAllTotalByBookedUserId(bookedUserId, idYear);
        revenueDTOList.stream().collect(Collectors.toMap(RevenueDTO::getMonth, RevenueDTO::getTotal));
        List<Double> moneyList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {

        }

        return null;
    }

    public List<Booking> getBookingsByBookedUserId(Long bookedUserId) {
        return iBookingRepository.findByBookedUserId(bookedUserId);
    }

    public List<Booking> getBookingsByBookedUserIdAndAccepted(Long bookedUserId) {
        return iBookingRepository.findByBookedUserIdAndStatus(bookedUserId, 2);
    }


    public List<Booking> getBookingsByBookingUserId(Long bookingUserId) {
        return iBookingRepository.findAllByBookingUser_Id(bookingUserId);
    }

    public Optional<Booking> findById(Long id) {
        return iBookingRepository.findById(id);
    }

}
