package com.ashish.splitwise.controller;

import com.ashish.splitwise.dto.UserLoginRequestDTO;
import com.ashish.splitwise.dto.UserSignupRequestDTO;
import com.ashish.splitwise.exception.InvalidEmailExpception;
import com.ashish.splitwise.exception.InvalidPasswordExpception;
import com.ashish.splitwise.exception.InvalidUserSignupInputException;
import com.ashish.splitwise.mapper.EntityToDTO;
import com.ashish.splitwise.model.User;
import com.ashish.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity userSignup(@RequestBody UserSignupRequestDTO userSignupRequestDTO){
        validUserSignup(userSignupRequestDTO);

        User savedUser = userService.signup(userSignupRequestDTO.getName(),userSignupRequestDTO.getEmail(),userSignupRequestDTO.getPassword());

        return ResponseEntity.ok(EntityToDTO.UserEtityToSignupResponse(savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody UserLoginRequestDTO userLoginRequestDTO){
        validUserLogin(userLoginRequestDTO);

        User savedUser = userService.login(userLoginRequestDTO.getEmail(),userLoginRequestDTO.getPassword());

        return ResponseEntity.ok(EntityToDTO.UserEtityToSignupResponse(savedUser));
    }

    public void validUserSignup(UserSignupRequestDTO userLoginRequestDTO){
        String emailRegex = "^(.+)@(\\S+) $";
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@#$%^&+=]).{8,}$";
        if(!Pattern.compile(emailRegex)
                .matcher(userLoginRequestDTO.getEmail())
                .matches()){
            throw new InvalidEmailExpception("Invalid email entered");
        }

        else if(!Pattern.compile(passwordRegex)
                .matcher(userLoginRequestDTO.getPassword())
                .matches()){
            throw new InvalidPasswordExpception("Entered password does not matches the criteria of new password");
        }
        else if(userLoginRequestDTO.getName() == null ||
                userLoginRequestDTO.getEmail() == null ||
                userLoginRequestDTO.getPassword() == null){
            throw  new InvalidUserSignupInputException("Invalid user inputs for signup");
        }
    }

    public void validUserLogin(UserLoginRequestDTO userLoginRequestDTO){

        if(
                userLoginRequestDTO.getEmail() == null ||
                userLoginRequestDTO.getPassword() == null){
            throw  new InvalidUserSignupInputException("Invalid user inputs for Login");
        }
    }

}
