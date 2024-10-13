package com.secure.notes2.service;

import com.secure.notes2.models.AppRole;
import com.secure.notes2.models.Role;
import com.secure.notes2.models.User;
import com.secure.notes2.repositories.RoleRepository;
import com.secure.notes2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public boolean updateUser(UserDetails user,User updateUser){
        User updateU = userRepository.findByUsername(user.getUsername()).
                orElseThrow(() -> new UsernameNotFoundException("user not found"));

        if (user!=null){
            updateU.setUsername(updateUser.getUsername());
            updateU.setPassword(updateUser.getPassword());
            userRepository.save(updateU);
            System.out.println(user.getPassword() + " " + user.getUsername());
            return true;
        }
        return false;
    }

    public boolean deleteUser(UserDetails userDetails){
        System.out.println(userDetails.getUsername());
        return userRepository.deleteUserByUsername(userDetails.getUsername());
    }

    public User createUser(User user){
        Role userRole = roleRepository.findByRoleName(AppRole.ROLE_USER).orElseGet(()
                -> roleRepository.save(new Role(AppRole.ROLE_USER)));

        User saveUser = new User();
        saveUser.setUsername(user.getUsername());
        saveUser.setEmail(user.getEmail());
        saveUser.setPassword(user.getPassword());
        saveUser.setAccountNonExpired(true);
        saveUser.setCredentialsNonExpired(true);
        saveUser.setEnabled(true);
        saveUser.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
        saveUser.setAccountExpiryDate(LocalDate.now().plusYears(1));
        saveUser.setTwoFactorEnabled(false);
        saveUser.setSignUpMethod("email");
        saveUser.setRole(userRole);
        userRepository.save(saveUser);

        return saveUser;
    }
}
