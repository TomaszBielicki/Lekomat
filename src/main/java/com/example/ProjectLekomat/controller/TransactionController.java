package com.example.ProjectLekomat.controller;

import com.example.ProjectLekomat.model.Transaction.TransactionRequest;
import com.example.ProjectLekomat.model.Transaction.TransactionResponse;
import com.example.ProjectLekomat.model.recipe.RecipeRequest;
import com.example.ProjectLekomat.model.recipe.RecipeResponse;
import com.example.ProjectLekomat.service.LockerService;
import com.example.ProjectLekomat.service.RecipeService;
import com.example.ProjectLekomat.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {


    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public TransactionResponse processTransaction(@RequestBody @Validated TransactionRequest transactionRequest ){
        return transactionService.processTransaction(transactionRequest);
    }
}
