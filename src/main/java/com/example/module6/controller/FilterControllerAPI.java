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

    @Autowired
    private UserService userService;
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam(required = false) Integer gender,
                                  @RequestParam(required = false) Long addressId,
                                  @RequestParam(required = false) Long viewCount,
                                  @RequestParam(required = false) Long rentCount,
                                  @RequestParam(required = false) Integer minAge,
                                  @RequestParam(required = false) Integer maxAge,
                                  @RequestParam(required = false) String username,
                                  @RequestParam(required = false) Integer status,
                                  @RequestParam(required = false) Integer viewCountOrder,
                                  @RequestParam(required = false) Integer rentCountOrder) {
        return userService.findByCriteria(gender, addressId, viewCount, rentCount,
                minAge, maxAge, username, status, viewCountOrder, rentCountOrder);
    }
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/search")
//    public List<User> searchUsers(@RequestParam(required = false) Integer gender,
//                                  @RequestParam(required = false) Long addressId,
//                                  @RequestParam(required = false) Long viewCount,
//                                  @RequestParam(required = false) Long rentCount,
//                                  @RequestParam(required = false) Integer minAge,
//                                  @RequestParam(required = false) Integer maxAge,
//                                  @RequestParam(required = false) String username,
//                                  @RequestParam(required = false) Integer status) {
//        return userService.findByCriteria(gender, addressId, viewCount, rentCount,
//                minAge, maxAge, username, status);
//    }
}
