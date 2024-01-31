package com.new_project.recipebook.service;

import com.new_project.recipebook.model.Recipe;
import com.new_project.recipebook.model.User;
import com.new_project.recipebook.repository.RecipeRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImplementation implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;


    @Override
    public Recipe createRecipe(@NotNull Recipe recipe, User user) {

        Recipe createdRecipe = new Recipe();
        createdRecipe.setTitle(recipe.getTitle());
        createdRecipe.setImage(recipe.getImage());
        createdRecipe.setDescription(recipe.getDescription());
        createdRecipe.setUser(user);
        createdRecipe.setCreatedAt(LocalDateTime.now());
        createdRecipe.setVegetarian(recipe.isVegetarian());

        return recipeRepository.save(createdRecipe);
    }

    @Override
    public Recipe findRecipeById(Long id) throws Exception {

        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            return optionalRecipe.get();
        }
            throw new Exception("Recipe not found with id " + id);
        }

    @Override
    public void deleteRecipe(Long Id) throws Exception {

        findRecipeById(Id);

        recipeRepository.deleteById(Id);


    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long Id) throws Exception {
        Recipe oldRecipe = findRecipeById(Id);


        if (recipe.getTitle() != null){
            oldRecipe.setTitle(recipe.getTitle());
        }
        if(recipe.getImage() != null){
            oldRecipe.setImage(recipe.getImage());
        }
        if(recipe.getDescription() != null){
            oldRecipe.setDescription(recipe.getDescription());
        }
      // oldRecipe.setVegetarian(recipe.isVegetarian());


        return recipeRepository.save(oldRecipe);
    }

    @Override
    public List<Recipe> findAllRecipes() {
        return recipeRepository.findAll();
    }

    //like or unlike recipe?
    @Override
    public Recipe likeRecipe(Long recipeId, User user) throws Exception {
        Recipe recipe = findRecipeById(recipeId);
        if (recipe.getLikes().contains(user.getId())){
            recipe.getLikes().remove(user.getId());
        } else {
            recipe.getLikes().add(user.getId());
        }
        return recipeRepository.save(recipe);
    }
}
