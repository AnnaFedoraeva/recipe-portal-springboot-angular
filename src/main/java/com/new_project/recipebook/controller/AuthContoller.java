package com.new_project.recipebook.controller;

import com.new_project.recipebook.config.JwtProvider;
import com.new_project.recipebook.model.User;
import com.new_project.recipebook.repository.UserRepository;
import com.new_project.recipebook.request.LoginRequest;
import com.new_project.recipebook.response.AuthResponse;
import com.new_project.recipebook.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthContoller {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailsService customUserDetails;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public AuthResponse createUser (@RequestBody User user) throws Exception{
         String email = user.getEmail();
         String password = user.getPassword();
         String fullName = user.getFullName();
         User isExistEmail = userRepository.findByEmail(email);
         if(isExistEmail != null) {
             throw new Exception("Email is already used.");
         }
         User createdUser = new User();
         createdUser.setEmail(email);
         createdUser.setPassword(passwordEncoder.encode(password));
         createdUser.setFullName(fullName);

         User savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse response = new AuthResponse();

        response.setJwt(token);
        response.setMessage("signup successfully");

        return response;

    }

    @PostMapping("/signin")
    public AuthResponse signinHandler (@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse();

        response.setJwt(token);
        response.setMessage("signin successfully");

        return response;

    }

    public Authentication authenticate (String username, String password){
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);
        if(userDetails == null){
            throw new BadCredentialsException("User not found.");

        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password.");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
