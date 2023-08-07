package com.example.module6.service.impl;

import com.example.module6.model.DTO.FilterDTO;
import com.example.module6.model.User;
import com.example.module6.repository.IFilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterService {
    @Autowired
    private IFilterRepository iFilterRepository;

//    public List<User> filter(FilterDTO filterDTO) {
//        return iFilterRepository.filterByAll(
//                filterDTO.getGender(),
//                filterDTO.getAddressId(),
//                filterDTO.getAgeRange().get(0),
//                filterDTO.getAgeRange().get(1),
//                filterDTO.getUsername()
//        );
//    }

}
