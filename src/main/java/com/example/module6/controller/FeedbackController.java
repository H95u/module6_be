package com.example.module6.controller;

import com.example.module6.model.Feedback;
import com.example.module6.service.impl.FeedbackService;
import jdk.vm.ci.meta.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @GetMapping("/receiver/{receiverId}")
    public ResponseEntity<List<Feedback>> getFeedbacksByReceiverId(@PathVariable Long receiverId) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByReceiverId(receiverId);
        return ResponseEntity.ok(feedbacks);
    }

    @PostMapping("/create")
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        feedback.setPresentTime(LocalDateTime.now());
        Feedback createdFeedback = feedbackService.createFeedback(feedback);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFeedback);
    }
}
