package com.ashish.splitwise.dto;

import com.ashish.splitwise.model.Group;
import com.ashish.splitwise.model.User;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserSignupReponseDTO {

    private String email;
    private String name;
    private int Id;
    private List<UserGroupResponseDTO> groupList;
    private List<UserFriendResponseDTO> friendsList;
}
