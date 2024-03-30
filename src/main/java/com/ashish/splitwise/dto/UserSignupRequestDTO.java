package com.ashish.splitwise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequestDTO {

    private String email;
    private String password;
    private String name;
}
