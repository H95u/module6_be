package com.example.module6.controller;

import com.example.module6.model.Report;
import com.example.module6.model.User;
import com.example.module6.request.CreateReportRequest;
import com.example.module6.service.impl.ReportService;
import com.example.module6.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Report>> findAll() {
        return new ResponseEntity<>(reportService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateReportRequest createReportRequest) {
        Report report = new Report();
        User accuser = userService.findOne(createReportRequest.getAccuserId()).orElse(null);
        User accused = userService.findOne(createReportRequest.getAccusedId()).orElse(null);
        report.setDescription(createReportRequest.getDescription());
        report.setAccused(accused);
        report.setAccuser(accuser);
        reportService.save(report);
        return new ResponseEntity<>(report, HttpStatus.CREATED);
    }
}
