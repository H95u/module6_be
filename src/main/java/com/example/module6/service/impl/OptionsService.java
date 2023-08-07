package com.example.module6.service.impl;

import com.example.module6.model.Options;
import com.example.module6.repository.IOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionsService {

    @Autowired
    private IOptionsRepository optionsRepository;

    public List<Options> findAll() {
        return optionsRepository.findAll();
    }
}
