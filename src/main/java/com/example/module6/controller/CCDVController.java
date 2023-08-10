package com.example.module6.controller;

import com.example.module6.model.UserBookingCountDTO;
import com.example.module6.model.UserBookingEndTimeDTO;
import com.example.module6.service.ICCDVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/ccdv")
public class CCDVController {
    @Autowired
    private ICCDVService iccdvService;

    @GetMapping("/top3-renters")
    public ResponseEntity<List<UserBookingCountDTO>> getTop3Renters(@RequestParam(required = false) Long ccdvId) {
        List<UserBookingCountDTO> top3Renters = iccdvService.findTop3Renters(ccdvId);
        if (top3Renters.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(top3Renters, HttpStatus.OK);
        }
    }

    @GetMapping("/top3-recent-renters")
    public ResponseEntity<List<UserBookingEndTimeDTO>> getTop3RecentRenters(@RequestParam(required = false) Long bookedUserId) {
        List<UserBookingEndTimeDTO> top3RecentRenters = iccdvService.findTop3RecentRenters(bookedUserId);
        if (top3RecentRenters.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(top3RecentRenters, HttpStatus.OK);
        }
    }

}
