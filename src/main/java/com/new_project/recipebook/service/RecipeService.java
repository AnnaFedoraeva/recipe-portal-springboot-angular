package com.new_project.recipebook.service;

import com.new_project.recipebook.model.Recipe;
import com.new_project.recipebook.model.User;

import java.util.List;

public interface RecipeService {

    public Recipe createRecipe (Recipe recipe, User user);
    public Recipe findRecipeById(Long id);

    public void deleteRecipe(Long id);

    public Recipe updateRecipe (Recipe recipe, Long Id);

    public List <Recipe> findAllRecipe();

    public Recipe likeRecipe (Long recipeId, User user);

}
