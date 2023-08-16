package com.example.module6.repository;
import com.example.module6.model.Booking;
import com.example.module6.model.DTO.RevenueDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookedUserId(Long bookedUserId);

    List<Booking> findAllByBookingUser_Id(Long bookingId);

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
}
