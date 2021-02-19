package com.example.ProjectLekomat.controller;

import com.example.ProjectLekomat.model.recipe.RecipeRequest;
import com.example.ProjectLekomat.model.recipe.RecipeResponse;
import com.example.ProjectLekomat.service.RecipeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {


    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public RecipeResponse findRecipeById(@PathVariable long id){
        return recipeService.findById(id);
    }

    @PostMapping
    public RecipeResponse addRecipe(@RequestBody @Validated RecipeRequest recipeRequest){
        return recipeService.addRecipe(recipeRequest);
    }
}
