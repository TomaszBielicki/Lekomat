package com.example.ProjectLekomat.service;

import com.example.ProjectLekomat.model.Transaction.TransactionRequest;
import com.example.ProjectLekomat.model.Transaction.TransactionResponse;
import com.example.ProjectLekomat.model.locker.LockerRequest;
import com.example.ProjectLekomat.model.locker.LockerResponse;
import com.example.ProjectLekomat.model.recipe.Recipe;
import com.example.ProjectLekomat.repo.RecipeRepo;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class TransactionService {

    private RecipeService recipeService;
    private LockerService lockerService;

    public TransactionService(RecipeService recipeService, LockerService lockerService) {
        this.recipeService = recipeService;
        this.lockerService = lockerService;
    }

    public TransactionResponse processTransaction(TransactionRequest transactionRequest){
        String hashName = transactionRequest.getName() + transactionRequest.getSurname() + transactionRequest.getPesel();
        String sha256hex = Hashing.sha256()
                .hashString(hashName, StandardCharsets.UTF_8)
                .toString();
        System.out.println(sha256hex);
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
