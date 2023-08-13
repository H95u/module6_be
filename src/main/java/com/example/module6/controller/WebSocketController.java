package com.example.module6.controller;

import com.example.module6.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class WebSocketController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) {
        return message;
    }
}
