package com.example.ProjectLekomat.repo;

import com.example.ProjectLekomat.model.medicine.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepo extends JpaRepository<Medicine, Long> {
}
