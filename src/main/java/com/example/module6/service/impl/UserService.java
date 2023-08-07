package com.example.module6.service.impl;

import com.example.module6.model.DTO.UserMappingOptionsDTO;
import com.example.module6.model.Options;
import com.example.module6.model.User;
import com.example.module6.model.UserPrinciple;
import com.example.module6.repository.IOptionsRepository;
import com.example.module6.repository.IUserRepository;
import com.example.module6.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService, IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    public User findByUsername(String username) {
        return iUserRepository.findAllByUsername(username);
    }

    public boolean add(User user) {
        iUserRepository.save(user);
        return true;
    }

    public UserDetails loadUserByUsername(String username) {
        List<User> users = iUserRepository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getUsername(), username)) {
                return UserPrinciple.build(user);
            }
        }
        return null;
    }

    @Transactional
    public List<Options> addOptionToUser(Long userId, List<Long> optionIds) {
        iUserRepository.deleteUserOptionByUserId(userId);
        for (Long optionId : optionIds) {
            iUserRepository.addOptionToUser(userId, optionId);
        }
        Optional<User> userOptional = iUserRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get().getOptions();
        }
        return Collections.emptyList();
    }

    @Override
    public List<User> findAll() {
        return iUserRepository.findAll();
    }

    public List<User> findAvailableUser() {
        return iUserRepository.findAvailableUser();
    }


    @Override
    public Optional<User> findOne(Long aLong) {
        return iUserRepository.findById(aLong);
    }

    @Override
    public void save(User user) {
        iUserRepository.save(user);
    }

    @Override
    public void delete(Long aLong) {
        iUserRepository.deleteById(aLong);
    }


    @Override
    public List<User> searchByUsername(String username) {
        return iUserRepository.searchByUsername(username);
    }

    public List<User> findByCriteria(Integer gender, Long addressId, Long viewCount, Long rentCount,
                                     Integer minAge, Integer maxAge, String username, Integer status) {
        return iUserRepository.findByCriteria(gender, addressId, viewCount, rentCount,
                minAge, maxAge, username, status);
    }
}