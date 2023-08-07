package com.example.module6.service.impl;

import com.example.module6.model.Chat;
import com.example.module6.repository.IChatRepository;
import com.example.module6.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService implements IChatService {
    @Autowired
    private IChatRepository iChatRepository;

    @Override
    public List<Chat> findAll() {
        return iChatRepository.findAll();
    }

    @Override
    public Optional<Chat> findOne(Long aLong) {
        return iChatRepository.findById(aLong);
    }

    @Override
    public void save(Chat chat) {
        iChatRepository.save(chat);
    }

    @Override
    public List<Chat> findChatByUserId(Long userId) {
        return iChatRepository.findChatByUserId(userId);
    }

    @Override
    public void delete(Long aLong) {
        iChatRepository.deleteById(aLong);
    }
}
