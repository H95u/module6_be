package com.example.module6.service.impl;

import com.example.module6.model.Feedback;
import com.example.module6.repository.IFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private IFeedbackRepository iFeedbackRepository;

    public List<Feedback> getFeedbacksByReceiverId(Long receiverId) {
        return iFeedbackRepository.findByReceiverId(receiverId);
    }

    public Feedback createFeedback(Feedback feedback) {
        return iFeedbackRepository.save(feedback);
    }
}
