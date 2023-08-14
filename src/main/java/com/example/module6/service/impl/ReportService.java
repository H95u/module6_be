package com.example.module6.service.impl;

import com.example.module6.model.Report;
import com.example.module6.repository.IReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private IReportRepository reportRepository;

    public List<Report> findAll() {
        return reportRepository.findAll();
    }
    public void save(Report report) {
        reportRepository.save(report);
    }
}
