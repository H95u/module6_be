package com.example.module6.service.impl;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private SimpMessagingTemplate messagingTemplate;
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    public void sendNotification(String receiverId){
        messagingTemplate.convertAndSendToUser(receiverId,"/queue/notifications", "Bạn có tin nhắn mới");
    }

}
