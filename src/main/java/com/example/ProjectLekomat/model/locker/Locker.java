package com.example.ProjectLekomat.model.locker;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.NotFound;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotFound
    private String lockerId;

    private long code;
    private boolean status;
    private String createAt;


}
