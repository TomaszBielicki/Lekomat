package com.example.ProjectLekomat.repo;

import com.example.ProjectLekomat.model.locker.Locker;
import com.example.ProjectLekomat.model.locker.LockerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LockerRepo extends JpaRepository<Locker, Long> {

    Locker findLockerById(long id);

}
