package com.example.ProjectLekomat.model.locker;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

@Getter
@Setter
public class LockerRequest {

    @NotNull
    private long code;

    private boolean status;


}
