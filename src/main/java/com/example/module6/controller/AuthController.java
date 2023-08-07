package com.example.module6.controller;

import com.example.module6.jwt.service.JwtResponse;
import com.example.module6.jwt.service.JwtService;
import com.example.module6.model.User;
import com.example.module6.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User userInfo = userService.findByUsername(user.getUsername());
        return ResponseEntity.ok(new ResponseEntity<>(userInfo, HttpStatus.OK));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        LocalDate nowDate = LocalDate.now();
        LocalDate dob = user.getDob();

        Period period = Period.between(dob, nowDate);
        int age = period.getYears();
        user.setImg("https://firebasestorage.googleapis.com/v0/b/module5-img.appspot.com/o/module6%2F8726291_robot_icon%20(1).png?alt=media&token=215d5e88-97f4-46a9-8ed6-4ca71ac62af8");
        user.setAge(age);
        boolean check = userService.add(user);
        if (check) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
