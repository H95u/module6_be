package com.example.module6.service;

import com.example.module6.model.User;
import java.util.List;

public interface IUserService extends IGeneralService<User, Long> {
   List<User> searchByUsername(String username);

}
