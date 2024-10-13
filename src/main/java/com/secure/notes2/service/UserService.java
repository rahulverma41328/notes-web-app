package com.secure.notes2.service;


import com.secure.notes2.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    boolean updateUser(UserDetails userDetails, User user);
    boolean deleteUser(UserDetails userDetails);
    User createUser(User user);
}
