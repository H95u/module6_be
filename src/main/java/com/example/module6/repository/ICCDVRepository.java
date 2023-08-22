package com.example.module6.repository;

import com.example.module6.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ICCDVRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT NEW com.example.module6.model.UserBookingCountDTO(b.bookingUser.id, COUNT(b.bookingUser), b.bookingUser, MAX(b.startTime), MAX(b.endTime)) " +
            "FROM Booking b " +
            "WHERE b.bookedUser.id = :ccdvId " +
            "GROUP BY b.bookingUser.id, b.bookingUser " +
            "ORDER BY COUNT(b.bookingUser) DESC")
    List<UserBookingCountDTO> findTop3Renters(@Param("ccdvId") Long ccdvId);




    @Query("SELECT NEW com.example.module6.model.UserBookingEndTimeDTO(b.bookedUser.id, b.bookingUser, b.startTime, b.endTime) " +
            "FROM Booking b " +
            "WHERE b.bookedUser.id = :bookedUserId " +
            "GROUP BY b.bookingUser.id, b.bookingUser, b.startTime, b.endTime " +
            "ORDER BY b.endTime DESC")
    List<UserBookingEndTimeDTO> findTop3RecentRenters(@Param("bookedUserId") Long bookedUserId);


}