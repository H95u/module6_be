package com.example.module6.service.impl;

import com.example.module6.model.User;
import com.example.module6.model.UserPrinciple;
import com.example.module6.repository.IUserRepository;
import com.example.module6.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

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
}
