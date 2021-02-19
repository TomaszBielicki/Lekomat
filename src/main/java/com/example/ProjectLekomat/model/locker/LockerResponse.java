package com.example.ProjectLekomat.model.locker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LockerResponse {

    @NotNull
    private long code;
    @NotNull
    private boolean status;
    private String createAt;

}
