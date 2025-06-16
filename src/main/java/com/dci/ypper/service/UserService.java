package com.dci.ypper.service;

import com.dci.ypper.model.User;
import com.dci.ypper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User register(User user) {
        return repository.save(user);
    }
}
