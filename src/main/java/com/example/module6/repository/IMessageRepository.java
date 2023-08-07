package com.example.module6.repository;

import com.example.module6.model.Chat;
import com.example.module6.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMessageRepository extends JpaRepository<Message,Long> {
    List<Message> findMessageByReceiverId(Long receiverId);
}
