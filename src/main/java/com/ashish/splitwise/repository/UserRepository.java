package com.ashish.splitwise.repository;

import com.ashish.splitwise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepository extends JpaRepository<User,Integer> {

    public User findUserByEmail(String email);
}
