package com.example.ProjectLekomat.service;

import com.example.ProjectLekomat.model.locker.Locker;
import com.example.ProjectLekomat.model.locker.LockerRequest;
import com.example.ProjectLekomat.model.locker.LockerResponse;
import com.example.ProjectLekomat.repo.LockerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LockerService {

    private final LockerRepo lockerRepo;

    public LockerService(LockerRepo lockerRepo) {
        this.lockerRepo = lockerRepo;
    }

    public LockerResponse findById(long id) {
        Optional<Locker> byId = lockerRepo.findById(id);
        Locker locker = byId.get();
        LockerResponse lockerResponse=new ModelMapper().map(locker, LockerResponse.class);
        return lockerResponse;
    }

    public void addLocker(LockerRequest lockerRequest){
        String lockerId = UUID.randomUUID().toString();
        String lockerCreatedTime = Date.from(Instant.now()).toString();
        Locker lockerEntity = new ModelMapper().map(lockerRequest, Locker.class);
        lockerEntity.setLockerId(lockerId);
        lockerEntity.setCreateAt(lockerCreatedTime);
        lockerRepo.save(lockerEntity);
    }
}
