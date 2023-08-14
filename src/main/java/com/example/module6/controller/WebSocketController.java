package com.example.module6.controller;

import com.example.module6.chat.MessageType;
import com.example.module6.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String sendMessage(String message) {
        System.out.println(message);
        return message;
    }

    @MessageMapping("/join")
    @SendTo("/topic/messages/join")
    public Message joinChat(Message message, SimpMessageHeaderAccessor headerAccessor) {
        if (headerAccessor.getSessionAttributes() != null) {
            headerAccessor.getSessionAttributes().put("username", message.getSender().getUsername());
            message.setType(MessageType.JOIN);
        }
        return message;
    }

    @MessageMapping("/leave")
    @SendTo("/topic/messages/leave")
    public Message leaveChat(Message message, SimpMessageHeaderAccessor headerAccessor) {
        if (headerAccessor.getSessionAttributes() != null) {
            headerAccessor.getSessionAttributes().put("username", message.getSender().getUsername());
            message.setType(MessageType.LEAVE);
        }
        return message;
    }
}
