package com.example.ProjectLekomat;

import com.example.ProjectLekomat.exception.NameNotFoundException;
import com.example.ProjectLekomat.model.Transaction.TransactionRequest;
import com.example.ProjectLekomat.model.Transaction.TransactionResponse;
import com.example.ProjectLekomat.service.LockerService;
import com.example.ProjectLekomat.service.RecipeService;
import com.example.ProjectLekomat.service.TransactionService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(TransactionService.class)
public class TransactionServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;
    @MockBean
    private LockerService lockerService;

    private List<TransactionRequest> transactionList = new ArrayList<>();

    @Test
    public void shouldCreateTransactionProcessWhenFoundOwner() throws Exception{
        transactionList.add(new TransactionRequest("Tomasz", "Bielicki", "12321312321"));

        RequestBuilder request = MockMvcRequestBuilders.post("/transaction")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\": \"Tomek\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();


    }
}
