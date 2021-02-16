package com.example.ProjectLekomat.model.locker;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LockerRequest {

    private long code;
    private boolean status;
    private String createAt;
}
