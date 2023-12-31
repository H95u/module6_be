package com.example.module6.controller;

import com.example.module6.model.DTO.ImageDTO;
import com.example.module6.model.Options;
import com.example.module6.model.User;
import com.example.module6.request.CreateOptionRequest;
import com.example.module6.request.UpdatePriceRequest;
import com.example.module6.request.UpdateStatusRequest;
import com.example.module6.request.UpdateUserRequest;
import com.example.module6.service.impl.AddressService;
import com.example.module6.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserControllerAPI {
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<User>> findAvailableUser() {
        return new ResponseEntity<>(userService.findAvailableUser(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/sort-by-view-count-desc")
    public ResponseEntity<List<User>> findAllViewCountDesc() {
        return new ResponseEntity<>(userService.findAllViewCountDesc(), HttpStatus.OK);
    }

    @GetMapping("/sort-by-rent-count-desc")
    public ResponseEntity<List<User>> findAllRentCountDesc() {
        return new ResponseEntity<>(userService.findAllRentCountDesc(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        Optional<User> userOptional = userService.findOne(id);
        if (userOptional.isPresent()) {
            userOptional.get().setViewCount(userOptional.get().getViewCount() + 1);
            userService.save(userOptional.get());
            return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> findOneInfo(@PathVariable Long id) {
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

    @PostMapping("/add-options/{id}")
    public ResponseEntity<?> addOptionToUser(@PathVariable Long id,
                                             @RequestBody CreateOptionRequest createOptionRequest) {
//        Long userId = 1L; // TODO: get userId from token
        List<Options> options = userService.addOptionToUser(id, createOptionRequest.getOptionIds());
        return new ResponseEntity<>(options, HttpStatus.CREATED);
    }

    @PostMapping("/update-price/{id}")
    public ResponseEntity<?> updatePriceByUserId(@PathVariable Long id,
                                                 @RequestBody UpdatePriceRequest updatePriceRequest) {
//        Long userId = 1L;
        Optional<User> userOptional = userService.findOne(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            userOptional.get().setPrice(updatePriceRequest.getPrice());
            userService.save(userOptional.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

    @PostMapping("/update-statusPartner/{id}")
    public ResponseEntity<?> updatePriceByUserId(@PathVariable Long id,
                                                 @RequestParam Integer status) {
        Optional<User> userOptional = userService.findOne(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            userOptional.get().setStatus(status);
            userService.save(userOptional.get());
            return new ResponseEntity<>(userOptional.get(), HttpStatus.ACCEPTED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody UpdateUserRequest updatedUser) {
        Optional<User> userOptional = userService.findOne(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setNickname(updatedUser.getNickname());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setDob(updatedUser.getDob());
            LocalDate nowDate = LocalDate.now();
            LocalDate dob = updatedUser.getDob();
            Period period = Period.between(dob, nowDate);
            int age = period.getYears();
            existingUser.setAge(age);
            existingUser.setAddress(addressService.findById(updatedUser.getAddressId()));
            existingUser.setGender(updatedUser.getGender());
            userService.save(existingUser);
            return new ResponseEntity<>(existingUser, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/search")
    public ResponseEntity<List<User>> searchAllByUsername(@RequestParam(required = false, defaultValue = "") String username) {
        List<User> tours = userService.searchByUsername(username);
        if (tours.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @GetMapping("/search-available")
    public ResponseEntity<List<User>> searchAvailableByUsername(@RequestParam(required = false, defaultValue = "") String username) {
        List<User> users = userService.searchByUsernameAndAvailable(username);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}


