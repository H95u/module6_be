package com.example.module6.service.impl;

import com.example.module6.model.Booking;
import com.example.module6.repository.IBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private IBookingRepository iBookingRepository;

    public Booking rentService (Booking booking) {
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

    public Booking rejectBooking(Long bookingId) {
        Booking booking = iBookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            booking.setStatus(3);
            return iBookingRepository.save(booking);
        }
        return null;
    }

}
