package com.example.ProjectLekomat.service;


import com.example.ProjectLekomat.exception.IdNotFoundException;
import com.example.ProjectLekomat.model.recipe.Recipe;
import com.example.ProjectLekomat.model.recipe.RecipeRequest;
import com.example.ProjectLekomat.model.recipe.RecipeResponse;
import com.example.ProjectLekomat.repo.RecipeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RecipeService {

    private final RecipeRepo recipeRepo;

    public RecipeService(RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    public RecipeResponse findById(long id) {
        Recipe recipe = recipeRepo.findById(id).orElseThrow(() -> new IdNotFoundException(id));
        return new ModelMapper().map(recipe, RecipeResponse.class);

    }

    public RecipeResponse addRecipe(RecipeRequest recipeRequest){
        String recipeId = UUID.randomUUID().toString();
        Recipe recipeEntity = new ModelMapper().map(recipeRequest, Recipe.class);
        String entityName = recipeEntity.getDescription();
        recipeEntity.setRecipeId(recipeId);
        recipeEntity.setDescription(entityName);
        recipeEntity.setOwner(recipeRequest.getOwner());

        recipeRepo.save(recipeEntity);

        return new ModelMapper().map(recipeEntity, RecipeResponse.class);
    }
    }

