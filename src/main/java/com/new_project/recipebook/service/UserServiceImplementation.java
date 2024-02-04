package com.new_project.recipebook.service;

import com.new_project.recipebook.config.JwtProvider;
import com.new_project.recipebook.model.User;
import com.new_project.recipebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isPresent()){
            return  optUser.get();
        }

        throw new Exception("user with id " + userId + " has not been found.");
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {

        String email =  jwtProvider.getEmailFromJwtToken(jwt);

        if(email == null){
            throw new Exception("Please provide a valid jwt token.");
        }

        User user = userRepository.findByEmail(email);

        if (user == null){
            throw new Exception("User not found with email " + email);
        }

        return user;
    }


}
