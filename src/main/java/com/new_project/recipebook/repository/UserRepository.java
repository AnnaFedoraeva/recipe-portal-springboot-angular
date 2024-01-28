package com.new_project.recipebook.repository;


import com.new_project.recipebook.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {

    public User findByEmail(String email);

    Boolean existsByEmail(String email);


    public void deleteById(@NotNull Long userId);
}
