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
            "ORDER BY CASE WHEN :sortPrice = 1 THEN u.price END ASC, " +
            "CASE WHEN :sortPrice = 0 THEN u.price END DESC, " +
            "u.price ASC")
    List<User> searchWithFilter(@Param("gender") Integer gender,
                                @Param("address") Long address,
                                @Param("name") String name,
                                @Param("minAge") Integer minAge,
                                @Param("maxAge") Integer maxAge,
                                @Param("sortPrice") Integer sortPrice);

    List<User> findAllByOptionsId(Long optionId);
}
