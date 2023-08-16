package com.example.module6.controller;

import com.example.module6.model.UserInfo;
import com.example.module6.service.impl.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user-info")
public class UserInfoControllerAPI {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping
    public ResponseEntity<List<UserInfo>> getAvailableUser() {
        return new ResponseEntity<>(userInfoService.getAvailableUser(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserInfo>> findAll() {
        return new ResponseEntity<>(userInfoService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody UserInfo userInfo) {
        Optional<UserInfo> userInfoUpdate = userInfoService.findOne(id);
        if (userInfoUpdate.isPresent()) {
            userInfoService.save(userInfo);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserInfo>> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(userInfoService.findOne(id), HttpStatus.OK);
    }
}
