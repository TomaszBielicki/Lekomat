package com.example.ProjectLekomat.service;

import com.example.ProjectLekomat.model.Transaction.TransactionRequest;
import com.example.ProjectLekomat.model.Transaction.TransactionResponse;
import com.example.ProjectLekomat.model.locker.LockerRequest;
import com.example.ProjectLekomat.model.locker.LockerResponse;
import com.example.ProjectLekomat.model.recipe.Recipe;
import com.example.ProjectLekomat.repo.RecipeRepo;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private RecipeService recipeService;
    private LockerService lockerService;

    public TransactionService(RecipeService recipeService, LockerService lockerService) {
        this.recipeService = recipeService;
        this.lockerService = lockerService;
    }

    public TransactionResponse processTransaction(TransactionRequest transactionRequest){
        recipeService.findByOwner(transactionRequest.getName());
        long generateCode =(int)(Math.random()*9000)+1000;

        LockerRequest lockerRequest = new LockerRequest(generateCode, true);
        LockerResponse lockerResponse = lockerService.addLocker(lockerRequest);
        TransactionResponse transactionResponse = new TransactionResponse();

        transactionResponse.setCode(generateCode);
        transactionResponse.setDate(lockerResponse.getCreateAt());
        return transactionResponse;
    }

}
