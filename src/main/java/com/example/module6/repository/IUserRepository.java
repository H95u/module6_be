package com.example.module6.repository;

import com.example.module6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findAllByUsername(String username);

    @Query(value = "select * from user where status = 1", nativeQuery = true)
    List<User> findAvailableUser();
}
