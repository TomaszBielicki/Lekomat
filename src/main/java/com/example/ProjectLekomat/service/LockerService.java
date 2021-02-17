package com.example.ProjectLekomat.service;

import com.example.ProjectLekomat.exception.IdNotFoundException;
import com.example.ProjectLekomat.model.locker.Locker;
import com.example.ProjectLekomat.model.locker.LockerRequest;
import com.example.ProjectLekomat.model.locker.LockerResponse;
import com.example.ProjectLekomat.repo.LockerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.*;

@Service
public class LockerService {

    private final LockerRepo lockerRepo;

    public LockerService(LockerRepo lockerRepo) {
        this.lockerRepo = lockerRepo;
    }

    public LockerResponse findById(long id) {
        Locker locker = lockerRepo.findById(id).orElseThrow(() -> new IdNotFoundException(id));
        return new ModelMapper().map(locker, LockerResponse.class);

    }

    public LockerResponse addLocker(LockerRequest lockerRequest){
        String lockerId = UUID.randomUUID().toString();
        String lockerCreatedTime = Date.from(Instant.now()).toString();
        Locker lockerEntity = new ModelMapper().map(lockerRequest, Locker.class);

        lockerEntity.setLockerId(lockerId);
        lockerEntity.setCreateAt(lockerCreatedTime);

        lockerRepo.save(lockerEntity);

        return new ModelMapper().map(lockerEntity, LockerResponse.class);
    }
}
