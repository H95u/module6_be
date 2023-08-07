package com.example.module6.controller;

import com.example.module6.model.Chat;
import com.example.module6.model.Message;
import com.example.module6.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/chats")
public class ChatControllerAPI {
    @Autowired
    private IChatService chatService;

    @GetMapping
    public ResponseEntity<List<Chat>> findAll() {
        return new ResponseEntity<>(chatService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Chat chat) {
        chatService.save(chat);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Chat chat) {
        Optional<Chat> chatOptional = chatService.findOne(id);
        if (chatOptional.isPresent()) {
            chat.setId(id);
            chatService.save(chat);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Chat>> delete(@PathVariable Long id) {
        Optional<Chat> chatOptional = chatService.findOne(id);
        if (chatOptional.isPresent()) {
            chatService.delete(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Chat>> findOne(@PathVariable Long id) {
        Optional<Chat> chatOptional = chatService.findOne(id);
        if (chatOptional.isPresent()) {
            return new ResponseEntity<>(chatOptional, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Chat>> findByUserId(@PathVariable Long id) {
        List<Chat> chatList = chatService.findChatByUserId(id);
        if (chatList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(chatList, HttpStatus.OK);
        }
    }

}
