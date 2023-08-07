package com.example.module6.controller;

import com.example.module6.model.Chat;
import com.example.module6.model.Message;
import com.example.module6.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/messages")
public class MessageControllerAPI {
    @Autowired
    private IMessageService messageService;

    @GetMapping
    public ResponseEntity<List<Message>> findAll() {
        return new ResponseEntity<>(messageService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Message message) {
        messageService.save(message);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Message message) {
        Optional<Message> messageOptional = messageService.findOne(id);
        if (messageOptional.isPresent()) {
            message.setId(id);
            messageService.save(message);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Message>> delete(@PathVariable Long id) {
        Optional<Message> messageOptional = messageService.findOne(id);
        if (messageOptional.isPresent()) {
            messageService.delete(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Message>> findOne(@PathVariable Long id) {
        Optional<Message> messageOptional = messageService.findOne(id);
        if (messageOptional.isPresent()) {
            return new ResponseEntity<>(messageOptional, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Message>> findByUserId(@PathVariable Long id) {
        List<Message> chatList = messageService.findMessageByReceiverId(id);
        if (chatList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(chatList, HttpStatus.OK);
        }
    }
}
