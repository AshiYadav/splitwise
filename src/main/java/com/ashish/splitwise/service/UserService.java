package com.ashish.splitwise.service;

import com.ashish.splitwise.model.User;
import org.springframework.stereotype.Service;


public interface UserService {

    public User signup(String name, String email, String password);
    public  User login (String email, String password);
}
