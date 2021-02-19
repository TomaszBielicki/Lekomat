package com.example.ProjectLekomat.controller;

import com.example.ProjectLekomat.model.locker.LockerResponse;
import com.example.ProjectLekomat.model.medicine.MedicineRequest;
import com.example.ProjectLekomat.model.medicine.MedicineResponse;
import com.example.ProjectLekomat.service.MedicineService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicine")
public class MedicineController {


    private MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/{id}")
    public MedicineResponse findMedicineById(@PathVariable long id){
        return medicineService.findById(id);
    }

    @PostMapping
    public MedicineResponse addMedicine(@RequestBody @Validated MedicineRequest medicineRequest){
        return medicineService.addMedicine(medicineRequest);
    }
}
