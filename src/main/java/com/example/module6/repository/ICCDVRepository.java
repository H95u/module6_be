package com.example.module6.repository;

import com.example.module6.model.User;
import com.example.module6.model.UserBookingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ICCDVRepository extends JpaRepository<User, Long> {

//    @Query("SELECT NEW com.example.module6.model.UserBookingDTO(b.bookingUser, b) " +
//            "FROM Booking b " +
//            "WHERE b.bookedUser.id = :ccdvId " +
//            "GROUP BY b.bookingUser, b.id " +
//            "ORDER BY COUNT(b) DESC")
//    List<UserBookingDTO> findTop3Renters(@Param("ccdvId") Long ccdvId);

    @Query("SELECT NEW com.example.module6.model.UserBookingDTO(COUNT(b.bookingUser), b.bookingUser) " +
            "FROM Booking b " +
            "WHERE b.bookedUser.id = :ccdvId " +
            "GROUP BY b.bookingUser " +
            "ORDER BY COUNT(b.bookingUser) DESC")
    List<UserBookingDTO> findTop3Renters(@Param("ccdvId") Long ccdvId);




    @Query("SELECT NEW com.example.module6.model.UserBookingDTO(b.bookingUser, b) " +
            "FROM Booking b " +
            "WHERE b.bookedUser.id = :bookedUserId " +
            "ORDER BY b.startTime DESC")
    List<UserBookingDTO> findTop3RecentRenters(@Param("bookedUserId") Long bookedUserId);

//    @Query("select NEW com.example.module6.model.CountBookingDTO(b.bookingUser,count(booking_user_id)) " +
//            "from Booking b " +
//            "where booking_user_id = :bookedUserId " +
//            "group by booking_user_id ")
//    List<UserBookingDTO> findTopRenters(@Param("bookedUserId") Long bookedUserId);



}