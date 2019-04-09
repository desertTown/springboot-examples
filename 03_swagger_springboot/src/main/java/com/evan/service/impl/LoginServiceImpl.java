package com.evan.service.impl;


import com.evan.pojo.User;
import com.evan.repository.UserRepository;
import com.evan.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean login(User user) {
        Optional<User> queryUser = userRepository.findByNameAndPassword(user.getName(), user.getPassword());
        if (queryUser.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
