package com.ashish.splitwise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "SPLITWISE_USER")
public class User extends BaseModel{

    private String email;
    private String name;
    private String password;
    @ManyToMany
    private List<Group> groupList;
    @ManyToMany
    private List<User> friendsList;

}
