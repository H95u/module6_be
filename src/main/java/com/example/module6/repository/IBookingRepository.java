package com.example.module6.repository;
import com.example.module6.model.Booking;
import com.example.module6.model.DTO.RevenueDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookedUserIdOrderByEndTimeDesc(Long bookedUserId);

    List<Booking> findAllByBookingUser_IdOrderByEndTimeDesc(Long bookingId);

    List<Booking> findByBookedUserIdAndStatus(Long bookedUserId, Integer status);

    @Query(value = "SELECT " +
            "   SUM(total) AS total, " +
            "   MONTH(end_time) AS `month` " +
            " FROM booking" +
            " WHERE booked_user_id = :bookedUserId" +
            "   AND YEAR(end_time) = :year " +
            "   AND status = 5" +
            " GROUP BY MONTH(end_time)", nativeQuery = true)
    List<RevenueDTO> findAllTotalByBookedUserId(@Param("bookedUserId") Long bookedUserId, @Param("year") Integer year);

    @Modifying
    @Query(value = "UPDATE booking SET status = 3" +
            " WHERE status = 2 " +
            "   AND DATE_ADD(end_time, INTERVAL 1 HOUR) <= NOW()", nativeQuery = true)
    void updateBookingComplete();


}
