package com.ashish.splitwise.service;

import com.ashish.splitwise.exception.InvalidUserException;
import com.ashish.splitwise.exception.UserWrongPasswordException;
import com.ashish.splitwise.model.User;
import com.ashish.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public User signup(String name, String email, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encoder.encode(password));
        return userRepository.save(user);

    }

    @Override
    public User login(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            throw new InvalidUserException("User does not exists");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(password,user.getPassword())){
            return user;
        }
        else{
            throw new UserWrongPasswordException("wrong password provided by used");
        }
    }
}
