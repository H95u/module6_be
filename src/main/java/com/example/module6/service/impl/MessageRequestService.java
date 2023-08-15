package com.example.module6.service.impl;

import com.example.module6.model.DTO.MessageRequestDTO;
import com.example.module6.model.Message;
import com.example.module6.model.User;
import com.example.module6.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageRequestService {
    @Autowired
    private MessageRepository messageRepository;

    public Message sendMessage(MessageRequestDTO messageRequest) {
        String content = messageRequest.getContent();
        Long senderId = messageRequest.getSenderId();
        Long receiverId = messageRequest.getReceiverId();

        User sender = new User();
        sender.setId(senderId);

        User receiver = new User();
        receiver.setId(receiverId);

        Message message = new Message();
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        message.setSender(sender);
        message.setReceiver(receiver);

        return messageRepository.save(message);
    }
    public List<Message> getReceivedMessages(Long receiverId){
        return messageRepository.findByReceiverId(receiverId);
    }
}
