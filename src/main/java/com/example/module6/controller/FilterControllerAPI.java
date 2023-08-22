package com.example.module6.controller;

import com.example.module6.model.DTO.FilterDTO;
import com.example.module6.model.User;
import com.example.module6.service.impl.FilterService;
import com.example.module6.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/filter")
public class FilterControllerAPI {
    @Autowired
    private FilterService filterService;

    @PostMapping
    public ResponseEntity<List<User>> findAvailableUser(@RequestBody FilterDTO filterDTO) {
        return new ResponseEntity<>(filterService.filter(filterDTO), HttpStatus.OK);
    }


    @PostMapping("/option/{id}")
    public ResponseEntity<List<User>> findByOption(@PathVariable Long id) {
        List<User> users = filterService.searchByOption(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
