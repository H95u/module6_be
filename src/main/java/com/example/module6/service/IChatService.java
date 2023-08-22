package com.example.module6.service;

import com.example.module6.exeption.IsSameUserException;
import com.example.module6.exeption.UserNotFoundException;
import com.example.module6.model.DTO.ChatChannelInitializationDTO;
import com.example.module6.model.DTO.ChatMessageDTO;
import org.springframework.beans.BeansException;

import java.util.List;

public interface IChatService {
    String establishChatSession(ChatChannelInitializationDTO chatChannelInitializationDTO)
            throws IsSameUserException, BeansException, UserNotFoundException;

    void submitMessage(ChatMessageDTO chatMessageDTO)
            throws BeansException, UserNotFoundException;

    List<ChatMessageDTO> getExistingChatMessages(String channelUuid);
}
