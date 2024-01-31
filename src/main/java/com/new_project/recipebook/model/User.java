package com.new_project.recipebook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String password;
    private String email;
    private String fullName;

    @Override
    public String toString() {
        return '\n'  + "User with id: " + id  +
                '\n'  + "name: " + fullName +
                + '\n'  + " email: " + email;
    }

}
