package com.example.module6.config;

import com.example.module6.model.Message;
import com.example.module6.service.impl.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private MessageService messageService;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new TextWebSocketHandler() {
            @Override
            protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
                String payload = message.getPayload();
                ObjectMapper objectMapper = new ObjectMapper();
                Message newMessage = objectMapper.readValue(payload, Message.class);
                newMessage.setTimestamp(LocalDateTime.now());
                messageService.save(newMessage);
                session.sendMessage(new TextMessage("Processed: " + payload));
            }
        }, "/api/message").setAllowedOrigins("*");
    }
}