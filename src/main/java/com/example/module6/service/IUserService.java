package com.example.module6.service;

import com.example.module6.model.User;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User, Long> {
   List<User> searchByUsername(String username);

}
