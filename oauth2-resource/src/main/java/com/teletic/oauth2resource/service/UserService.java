package com.teletic.oauth2resource.service;

import com.teletic.oauth2resource.entity.User;

import java.util.Optional;

public interface UserService {

    void save(User user);

    Optional<User> findByUsername(String username);
}
