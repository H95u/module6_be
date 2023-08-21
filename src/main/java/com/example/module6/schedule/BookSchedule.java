package com.example.module6.schedule;

import com.example.module6.repository.IBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BookSchedule {
    @Autowired
    private IBookingRepository iBookingRepository;

    @Scheduled(initialDelay = 1000, fixedDelay = 60000)
    public void updateBookingCompleteSchedule() {
        iBookingRepository.updateBookingComplete();
    }
}
