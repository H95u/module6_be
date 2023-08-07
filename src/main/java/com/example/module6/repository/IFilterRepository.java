package com.example.module6.repository;

import com.example.module6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFilterRepository extends JpaRepository<User, Long> {
    List<User> findAllByGenderAndAddressIdAndViewCountAndRentCountAndAgeBetweenAndUsernameContainingIgnoreCase(
            Integer gender,
            Long addressId,
            Long viewCount,
            Long rentCount,
            Integer minAge,
            Integer maxAge,
            String username
    );
}
