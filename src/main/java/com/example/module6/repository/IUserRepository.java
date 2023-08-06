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
}
