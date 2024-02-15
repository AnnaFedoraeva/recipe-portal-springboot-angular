package com.new_project.recipebook.controller;

import com.new_project.recipebook.model.Recipe;
import com.new_project.recipebook.model.User;
import com.new_project.recipebook.repository.UserRepository;
import com.new_project.recipebook.service.RecipeService;
import com.new_project.recipebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return recipeService.createRecipe(recipe, user);
    }

    @GetMapping()
    public List<Recipe> getAllRecipes() throws Exception {
        return recipeService.findAllRecipes();
    }

    //delete any recipe?
    @DeleteMapping("/{recipeId}")
    public ResponseEntity<?> deleteRecipe (@PathVariable Long recipeId) throws Exception {
        recipeService.deleteRecipe(recipeId);
        return new ResponseEntity<>("The recipe with id " + recipeId + " has been successfully deleted", HttpStatus.OK);
    }

    @PutMapping ("/{recipeId}")
    public ResponseEntity<?> updateRecipe (@PathVariable Long recipeId, @RequestBody Recipe recipe) throws Exception{
        Recipe updatedRecipe = recipeService.updateRecipe(recipe, recipeId);
        return new ResponseEntity<>( "The recipe with id " + recipeId + " has been successfully updated. Updated recipe: " + updatedRecipe, HttpStatus.OK);
    }

    @PutMapping("/{recipeId}/like")
    public ResponseEntity<?> likeRecipe (@PathVariable Long recipeId, @RequestHeader("Authorization") String jwt) throws  Exception{
        User user = userService.findUserByJwt(jwt);
        Recipe likedRecipe = recipeService.likeRecipe(recipeId, user);
        return new ResponseEntity<>(likedRecipe, HttpStatus.OK);
    }








}
