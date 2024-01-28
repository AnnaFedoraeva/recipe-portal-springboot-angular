package com.new_project.recipebook.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToOne
    private User user;

    private String image;

    private String description;

    private boolean vegetarian;

    private LocalDateTime createdAt;

    private List<Long> likes = new ArrayList<>();

}
