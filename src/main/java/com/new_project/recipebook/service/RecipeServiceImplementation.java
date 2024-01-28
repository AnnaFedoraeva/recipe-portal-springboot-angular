package com.new_project.recipebook.service;

import com.new_project.recipebook.model.Recipe;
import com.new_project.recipebook.model.User;
import com.new_project.recipebook.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImplementation implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;


    @Override
    public Recipe createRecipe(Recipe recipe, User user) {
        return null;
    }

    @Override
    public Recipe findRecipeById(Long id) {
        return null;
    }

    @Override
    public void deleteRecipe(Long id) {

    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long Id) {
        return null;
    }

    @Override
    public List<Recipe> findAllRecipe() {
        return null;
    }

    @Override
    public Recipe likeRecipe(Long recipeId, User user) {
        return null;
    }
}
