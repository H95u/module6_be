package com.example.module6.controller;

import com.example.module6.model.Booking;
import com.example.module6.model.User;
import com.example.module6.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/paypal")
public class PaypalControllerAPI {

    @Autowired
    private UserService userService;

    @PostMapping("/deposit/{id}")
    public ResponseEntity<?> depositMoney(@PathVariable Long id,
                                          @RequestParam Double amount) {
        Optional<User> user = userService.findOne(id);
        if (user.isPresent()) {
            user.get().setMoney(user.get().getMoney() + amount);
            userService.save(user.get());
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

}
