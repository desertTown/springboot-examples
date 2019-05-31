package com.evan.saas.tenant.service;


import com.evan.saas.tenant.model.User;

public interface UserService {

    void save(User user);

    User findById(String userId);

    User findByUsername(String username);
}
