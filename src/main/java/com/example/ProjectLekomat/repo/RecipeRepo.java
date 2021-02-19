package com.example.ProjectLekomat.repo;

import com.example.ProjectLekomat.model.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepo extends JpaRepository<Recipe, Long> {
}
