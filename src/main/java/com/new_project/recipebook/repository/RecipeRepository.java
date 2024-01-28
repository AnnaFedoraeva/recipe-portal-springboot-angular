package com.new_project.recipebook.repository;

import com.new_project.recipebook.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository <Recipe, Long> {




}
