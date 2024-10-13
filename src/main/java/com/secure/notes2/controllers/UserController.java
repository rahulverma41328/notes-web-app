package com.secure.notes2.controllers;

import com.secure.notes2.models.User;
import com.secure.notes2.repositories.RoleRepository;
import com.secure.notes2.repositories.UserRepository;
import com.secure.notes2.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserServiceImp userService;

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user){

        return  userService.createUser(user);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@AuthenticationPrincipal UserDetails userDetails,@RequestBody User user){
        if (userService.updateUser(userDetails,user)){
            return new ResponseEntity<>("user details updated",HttpStatus.OK);
        }
        return new ResponseEntity<>("user details not updated at this time",HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/getUser")
    public ResponseEntity<String> getUser(@AuthenticationPrincipal UserDetails userDetails){
        return new ResponseEntity<>("nothing",HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@AuthenticationPrincipal UserDetails userDetails){
        if(userService.deleteUser(userDetails)){
            return new ResponseEntity<>("User deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong",HttpStatus.FAILED_DEPENDENCY);
    }
}
