package com.example.module6.service.impl;

import com.example.module6.model.UserInfo;
import com.example.module6.repository.IUserInfoRepository;
import com.example.module6.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements IUserInfoService {
    @Autowired
    private IUserInfoRepository iUserInfoRepository;

    @Override
    public List<UserInfo> findAll() {
        return iUserInfoRepository.findAll();
    }

    @Override
    public Optional<UserInfo> findOne(Long aLong) {
        return iUserInfoRepository.findById(aLong);
    }

    @Override
    public void save(UserInfo userInfo) {
        iUserInfoRepository.save(userInfo);
    }

    @Override
    public void delete(Long aLong) {
        iUserInfoRepository.deleteById(aLong);
    }

    public List<UserInfo> getAvailableUser() {
        return iUserInfoRepository.getAvailableUser();
    }

}
