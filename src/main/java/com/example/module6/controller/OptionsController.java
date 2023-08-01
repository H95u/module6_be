package com.example.module6.controller;

import com.example.module6.model.Options;
import com.example.module6.service.impl.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/options")
public class OptionsController {

    @Autowired
    private OptionsService optionsService;

    @GetMapping
    public ResponseEntity<List<Options>> findAll() {
        return new ResponseEntity<>(optionsService.findAll(), HttpStatus.OK);
    }
}
