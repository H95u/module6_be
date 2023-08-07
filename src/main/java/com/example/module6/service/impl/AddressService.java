package com.example.module6.service.impl;

import com.example.module6.model.Address;
import com.example.module6.repository.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private IAddressRepository addressRepository;

    public List<Address> findAll() {
      return addressRepository.findAll();
    }

}
