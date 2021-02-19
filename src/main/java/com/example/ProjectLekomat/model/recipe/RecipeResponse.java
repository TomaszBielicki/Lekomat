package com.example.ProjectLekomat.model.recipe;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeResponse {

    @NotNull
    @NotFound
    private String recipeId;
    @NotNull
    private String description;
    private String owner;

}
