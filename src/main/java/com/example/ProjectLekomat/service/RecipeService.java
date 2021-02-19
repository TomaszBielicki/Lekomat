package com.example.ProjectLekomat.service;


import com.example.ProjectLekomat.exception.IdNotFoundException;
import com.example.ProjectLekomat.exception.NameNotFoundException;
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
        recipeEntity.setOwnerHash(recipeRequest.getOwnerHash());

        recipeRepo.save(recipeEntity);

        return new ModelMapper().map(recipeEntity, RecipeResponse.class);
    }

    public void findByOwner(String hash){
        Recipe recipe = recipeRepo.findByOwnerHash(hash);

        if(recipe != null && recipe.isStatus()){
            //todo ustawić status na bazie
            recipe.setStatus(false);
            return;
        }
        throw new NameNotFoundException(hash);
    }
    }

