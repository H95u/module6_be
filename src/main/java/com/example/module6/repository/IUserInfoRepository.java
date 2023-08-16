package com.example.module6.repository;

import com.example.module6.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserInfoRepository extends JpaRepository<UserInfo, Long> {
    @Query(value = "select * from user_info where status = 1", nativeQuery = true)
    List<UserInfo> getAvailableUser();
}
