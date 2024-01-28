package com.new_project.recipebook.controller;

import com.new_project.recipebook.model.User;
import com.new_project.recipebook.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody @NotNull User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("Email " + user.getEmail() + " is already used", HttpStatus.OK);
        } else {
            User savedUser = userRepository.save(user);
            return new ResponseEntity<>("The user " + savedUser + " has been successfully saved", HttpStatus.OK);
        }
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable @NotNull Long userId) {
        Optional<User> userToDelete = userRepository.findById(userId);
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return new ResponseEntity<>("The user " + userToDelete + " has been deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The user " + userToDelete  + " does not exist", HttpStatus.OK);
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers (){
        List <User> users = userRepository.findAll();
        return users;
    }




}



