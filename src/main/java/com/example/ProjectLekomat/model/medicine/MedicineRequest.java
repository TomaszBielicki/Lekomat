package com.example.ProjectLekomat.model.medicine;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineRequest {

    @NotNull
    @NotFound
    private String medicineId;
    private String name;
}
