package com.example.module6.repository;

import com.example.module6.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookedUserId(Long bookedUserId);
}
