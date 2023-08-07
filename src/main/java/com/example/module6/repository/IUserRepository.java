package com.example.module6.repository;

import com.example.module6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findAllByUsername(String username);

    @Query(value = "select * from lover_module6.user where status = 1", nativeQuery = true)
    List<User> findAvailableUser();

    @Modifying
    @Query(value = "INSERT INTO lover_module6.user_options (user_id, options_id) VALUES (:userId, :optionId)", nativeQuery = true)
    void addOptionToUser(@Param("userId") Long userId, @Param("optionId") Long optionId);

    @Modifying
    @Query(value = "DELETE FROM lover_module6.user_options WHERE user_id = :userId", nativeQuery = true)
    void deleteUserOptionByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT u FROM User u WHERE u.username LIKE %:username%")
    List<User> searchByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u " +
            "WHERE (:gender IS NULL OR u.gender = :gender) " +
            "AND (:addressId IS NULL OR u.address.id = :addressId) " +
            "AND (:minAge IS NULL OR u.age >= :minAge) " +
            "AND (:maxAge IS NULL OR u.age <= :maxAge) " +
            "AND (:username IS NULL OR UPPER(u.username) LIKE CONCAT('%', UPPER(:username), '%')) " +
            "AND (:status IS NULL OR u.status = :status) " +
            "ORDER BY " +
            "CASE WHEN :viewCount IS NOT NULL AND :viewCountOrder = 1 THEN u.viewCount END ASC, " +
            "CASE WHEN :viewCount IS NOT NULL AND :viewCountOrder != 1 THEN u.viewCount END DESC, " +
            "CASE WHEN :rentCount IS NOT NULL AND :rentCountOrder = 1 THEN u.rentCount END ASC, " +
            "CASE WHEN :rentCount IS NOT NULL AND :rentCountOrder != 1 THEN u.rentCount END DESC, " +
            "CASE WHEN :viewCount IS NULL AND :rentCount IS NULL THEN u.id END ASC")
    List<User> findByCriteria(
            @Param("gender") Integer gender,
            @Param("addressId") Long addressId,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            @Param("username") String username,
            @Param("status") Integer status,
            @Param("viewCount") Long viewCount,
            @Param("rentCount") Long rentCount,
            @Param("viewCountOrder") Integer viewCountOrder,
            @Param("rentCountOrder") Integer rentCountOrder
    );






}
