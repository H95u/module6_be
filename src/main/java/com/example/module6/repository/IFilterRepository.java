package com.example.module6.repository;

import com.example.module6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFilterRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE " +
            "(:gender IS NULL OR u.gender = :gender) " +
            "AND (:address IS NULL OR u.address.id = :address) " +
            "AND (:name IS NULL OR u.username LIKE %:name%) " +
            "AND (u.age BETWEEN :minAge AND :maxAge) " +
            "AND u.status = 1 " +
            "ORDER BY CASE WHEN :viewCount = 1 THEN u.viewCount END ASC, " +
            "CASE WHEN :viewCount = 0 THEN u.viewCount END DESC, " +
            "CASE WHEN :rentCount = 1 THEN u.rentCount END ASC, " +
            "CASE WHEN :rentCount = 0 THEN u.rentCount END DESC")
    List<User> searchWithFilter(@Param("gender") Integer gender,
                                @Param("address") Long address,
                                @Param("name") String name,
                                @Param("minAge") Integer minAge,
                                @Param("maxAge") Integer maxAge,
                                @Param("viewCount") Long viewCount,
                                @Param("rentCount") Long rentCount);
    List<User> findAllByOptionsId(Long optionId);
}
