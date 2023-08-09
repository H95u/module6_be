package com.example.module6.repository;

import com.example.module6.model.Feedback;
import com.example.module6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByReceiverId(Long receiverId);
}
