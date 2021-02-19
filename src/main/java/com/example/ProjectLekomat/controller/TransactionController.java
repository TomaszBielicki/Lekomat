package com.example.ProjectLekomat.controller;

import com.example.ProjectLekomat.model.recipe.RecipeRequest;
import com.example.ProjectLekomat.model.recipe.RecipeResponse;
import com.example.ProjectLekomat.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TransactionController {


    private RecipeService recipeService;

    public TransactionController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public RecipeResponse getRecipe(@RequestBody @Validated RecipeResponse recipeResponse ){
        return null;
    }
}
