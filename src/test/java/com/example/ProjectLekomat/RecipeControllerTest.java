package com.example.ProjectLekomat;

import com.example.ProjectLekomat.controller.RecipeController;
import com.example.ProjectLekomat.exception.IdNotFoundException;
import com.example.ProjectLekomat.model.recipe.RecipeResponse;
import com.example.ProjectLekomat.service.RecipeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RecipeService recipeService;
    private List<RecipeResponse> recipeList = new ArrayList<>();

    @Test
    public void shouldReturnRecipeById() throws Exception{
        recipeList.add( new RecipeResponse("123", "gripex", "Tomek"));
        when(recipeService.findById(1L)).thenReturn(recipeList.get(0));
        RequestBuilder request = MockMvcRequestBuilders.get("/recipe/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"recipeId\": \"123\",\n" +
                        "    \"description\": \"gripex\",\n" +
                        "    \"owner\": \"Tomek\"\n" +
                        "}")).andReturn();
    }

    @Test
    public void shouldNotReturnRecipeByIdWhenNotFound() throws Exception{
        when(recipeService.findById(1L)).thenThrow(new IdNotFoundException(1));
        RequestBuilder request = MockMvcRequestBuilders.get("/recipe/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isNotFound())
                .andExpect(content().string("Id: 1 not found")).andReturn();
    }

    @Test
    public void shouldCreateRecipe() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.post("/recipe")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"name\": \"gripex\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }

}
