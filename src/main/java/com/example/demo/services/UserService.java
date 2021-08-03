package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserExistsException;

public interface UserService {

    User createUser(String id, String name) throws UserExistsException;
}
