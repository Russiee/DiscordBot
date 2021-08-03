package com.example.demo.services.impl;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserExistsException;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(String id, String name) throws UserExistsException {
        Optional<User> userOpt = userRepository.findUserById(id);
        if(userOpt.isPresent()) {
            throw new UserExistsException(id, name);
        }
        User user = new User(id, name);
        user = userRepository.save(user);
        return user;
    }
}
