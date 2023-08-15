package com.example.module6.controller;

import com.example.module6.model.DTO.MessageRequestDTO;
import com.example.module6.model.Message;
import com.example.module6.service.impl.MessageRequestService;
import com.example.module6.service.impl.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/sends")
public class MessageController {
    @Autowired
    private MessageRequestService messageRequestService;
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/sender")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequestDTO messageRequestDTO){
        Message message = messageRequestService.sendMessage(messageRequestDTO);
        if(message == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        notificationService.sendNotification(messageRequestDTO.getReceiverId().toString());
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping("/received/{receiverId}")
    public ResponseEntity<List<Message>> getReceivedMessages(@PathVariable Long receiverId){
        List<Message> messages = messageRequestService.getReceivedMessages(receiverId);
        if(messages == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(messages,HttpStatus.OK);
    }

}
