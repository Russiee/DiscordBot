package com.example.demo.services.impl;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserExistsException;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(String id, String name) throws UserExistsException {
        if(userRepository.findUserById(id).isPresent()) {
            throw new UserExistsException(id, name);
        }
        User user = new User(id, name);
        return userRepository.save(user);
    }
}
