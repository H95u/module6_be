package com.example.module6.controller;

import com.example.module6.model.DTO.ImageDTO;
import com.example.module6.model.Options;
import com.example.module6.model.User;
import com.example.module6.request.CreateOptionRequest;
import com.example.module6.request.UpdatePriceRequest;
import com.example.module6.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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

    @PostMapping("/add-options")
    public ResponseEntity<?> addOptionToUser(@RequestBody CreateOptionRequest createOptionRequest) {
        Long userId = 1L; // TODO: get userId from token
        List<Options> options = userService.addOptionToUser(userId, createOptionRequest.getOptionIds());
        return new ResponseEntity<>(options, HttpStatus.CREATED);
    }

    @PostMapping("/update-price")
    public ResponseEntity<?> updatePriceByUserId(@RequestBody UpdatePriceRequest updatePriceRequest) {
        Long userId = 1L;
        Optional<User> userOptional = userService.findOne(userId);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            userOptional.get().setPrice(updatePriceRequest.getPrice());
            userService.save(userOptional.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }


    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchByTitle(@RequestParam(required = false, defaultValue = "") String username) {
        List<User> tours = userService.searchByUsername(username);
        if (tours.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @Autowired
    private IUserRepository userRepository;

    @GetMapping("/filter")
    public List<User> getUsers(
            @RequestParam(name = "gender", required = false) Integer gender,
            @RequestParam(name = "address", required = false) Long addressId,
            @RequestParam(name = "viewCount", required = false) Long viewCount,
            @RequestParam(name = "rentCount", required = false) Long rentCount,
            @RequestParam(name = "minAge", required = false) Integer minAge,
            @RequestParam(name = "maxAge", required = false) Integer maxAge,
            @RequestParam(name = "username", required = false) String username
    ) {
        if (gender == null || addressId == null || viewCount == null || rentCount == null || minAge == null || maxAge == null || username == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vui lòng nhập đầy đủ các trường tìm kiếm.");
        }

        List<User> users = userRepository.findAllByGenderAndAddressIdAndViewCountAndRentCountAndAgeBetweenAndUsernameContainingIgnoreCase(
                gender,
                addressId,
                viewCount,
                rentCount,
                minAge,
                maxAge,
                username
        );

        return users;
    }

}


