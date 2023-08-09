package com.example.module6.repository;

import com.example.module6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ICCDVRepository extends JpaRepository<User, Long> {

    @Query("SELECT b.bookingUser " +
            "FROM Booking b " +
            "WHERE b.bookedUser.id = :ccdvId " +
            "GROUP BY b.bookingUser " +
            "ORDER BY COUNT(b) DESC ")
    List<User> findTop3Renters(@Param("ccdvId") Long ccdvId);

    @Query("SELECT b.bookingUser " +
            "FROM Booking b " +
            "WHERE b.bookedUser.id = :bookedUserId " +
            "ORDER BY b.startTime DESC ")
    List<User> findTop3RecentRenters(@Param("bookedUserId") Long bookedUserId);


}