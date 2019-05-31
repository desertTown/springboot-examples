package com.evan.saas.tenant.service.impl;


import com.evan.saas.common.util.TwitterIdentifier;
import com.evan.saas.context.TenantContextHolder;
import com.evan.saas.tenant.model.User;
import com.evan.saas.tenant.repository.UserRepository;
import com.evan.saas.tenant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private static TwitterIdentifier identifier = new TwitterIdentifier();



    @Override
    public void save(User user) {
        user.setId(identifier.generalIdentifier());
        user.setTenant(TenantContextHolder.getTenant());
        userRepository.save(user);
    }

    @Override
    public User findById(String userId) {
        Optional<User> optional = userRepository.findById(userId);
        if(optional.isPresent()){
            return optional.get();
        }else{
            return null;
        }
    }

    @Override
    public User findByUsername(String username) {
        System.out.println(TenantContextHolder.getTenant());
        return userRepository.findByUsername(username);
    }
}
