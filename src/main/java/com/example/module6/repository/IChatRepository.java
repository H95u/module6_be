package com.example.module6.repository;

import com.example.module6.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findChatByUserId(Long userId);
}
