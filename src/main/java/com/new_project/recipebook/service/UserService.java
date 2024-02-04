package com.new_project.recipebook.service;

import com.new_project.recipebook.model.User;

public interface UserService {

    public User findUserById (Long userId) throws Exception;

    public User findUserByJwt(String jwt) throws Exception;


}
