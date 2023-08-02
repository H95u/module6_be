package com.example.module6.controller;

import com.example.module6.model.DTO.ImageDTO;
import com.example.module6.model.Options;
import com.example.module6.model.User;
import com.example.module6.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserControllerAPI {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAvailableUser() {
        return new ResponseEntity<>(userService.findAvailableUser(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Optional<User> userOptional = userService.findOne(id);
        if (userOptional.isPresent()) {
            return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/change-avatar/{id}")
    public ResponseEntity<?> changeAvatar(@PathVariable Long id,
                                          @RequestBody ImageDTO imageDTO) {
        Optional<User> userOptional = userService.findOne(id);
        if (userOptional.isPresent()) {
            userOptional.get().setImg(imageDTO.getImg());
            userService.save(userOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
