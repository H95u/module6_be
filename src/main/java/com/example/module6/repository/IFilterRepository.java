package com.example.module6.repository;

import com.example.module6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFilterRepository extends JpaRepository<User, Long> {
//    @Query(value = "select * from user where" +
//            " gender = :gender and age between :minAge and :maxAge" +
//            " and address_id = :address;", nativeQuery = true)
//    List<User> filterByAll(
//            @Param("gender") Integer gender,
//            @Param("address")  Long addressId,
//            Integer minAge,
//            Integer maxAge,
//            String username
//    );


}
