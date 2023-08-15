package com.example.module6.service.impl;


import com.example.module6.model.Message;
import com.example.module6.repository.IMessageRepository;
import com.example.module6.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements IMessageService {
    @Autowired
    private IMessageRepository iMessageRepository;

    @Override
    public List<Message> findAll() {
        return iMessageRepository.findAll();
    }

    @Override
    public Optional<Message> findOne(Long aLong) {
        return iMessageRepository.findById(aLong);
    }

    @Override
    public void save(Message message) {
        iMessageRepository.save(message);
    }

    @Override
    public void delete(Long aLong) {
        iMessageRepository.deleteById(aLong);
    }

    @Override
    public List<Message> findMessageByReceiverId(Long receiverId) {
        return iMessageRepository.findMessageByReceiverId(receiverId);
    }

    @Override
    public Message saveMessage(Message message) {
        return iMessageRepository.save(message);
    }
}
