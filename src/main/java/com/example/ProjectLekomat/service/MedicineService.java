package com.example.ProjectLekomat.service;


import com.example.ProjectLekomat.exception.IdNotFoundException;
import com.example.ProjectLekomat.model.locker.Locker;
import com.example.ProjectLekomat.model.locker.LockerResponse;
import com.example.ProjectLekomat.model.medicine.Medicine;
import com.example.ProjectLekomat.model.medicine.MedicineRequest;
import com.example.ProjectLekomat.model.medicine.MedicineResponse;
import com.example.ProjectLekomat.repo.MedicineRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class MedicineService {

    private final MedicineRepo medicineRepo;

    public MedicineService(MedicineRepo medicineRepo) {
        this.medicineRepo = medicineRepo;
    }

    public MedicineResponse findById(long id) {
        Medicine medicine = medicineRepo.findById(id).orElseThrow(() -> new IdNotFoundException(id));
        return new ModelMapper().map(medicine, MedicineResponse.class);

    }

    public MedicineResponse addMedicine(MedicineRequest medicineRequest){
        String medicineId = UUID.randomUUID().toString();
        Medicine medicineEntity = new ModelMapper().map(medicineRequest, Medicine.class);
        String entityName = medicineEntity.getName();
        medicineEntity.setMedicineId(medicineId);
        medicineEntity.setName(entityName);

        medicineRepo.save(medicineEntity);

        return new ModelMapper().map(medicineEntity, MedicineResponse.class);
    }
    }

