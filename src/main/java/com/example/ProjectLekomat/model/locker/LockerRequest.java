package com.example.ProjectLekomat.model.locker;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LockerRequest {

    @NotNull
    private long    code;

    @NotNull
    private boolean status;


}
