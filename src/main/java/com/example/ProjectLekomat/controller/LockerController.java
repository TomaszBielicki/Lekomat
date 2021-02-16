package com.example.ProjectLekomat.controller;


import com.example.ProjectLekomat.model.locker.Locker;
import com.example.ProjectLekomat.model.locker.LockerRequest;
import com.example.ProjectLekomat.model.locker.LockerResponse;
import com.example.ProjectLekomat.service.LockerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lock")
public class LockerController {

    private LockerService lockerService;

    public LockerController (LockerService lockerService){
        this.lockerService = lockerService;
    }

       @GetMapping("/{id}")
        public LockerResponse findLockerById(@PathVariable long id){
        return lockerService.findById(id);
        }

        @PostMapping
        public void addLocker(@RequestBody @Validated LockerRequest lockerRequest){
        lockerService.addLocker(lockerRequest);
        }
}
