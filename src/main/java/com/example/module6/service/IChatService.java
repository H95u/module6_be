package com.example.module6.service;

import com.example.module6.model.Chat;

import java.util.List;
import java.util.Optional;

public interface IChatService extends IGeneralService<Chat, Long> {
    List<Chat> findChatByUserId(Long userId);
}
