package com.homework.jpahibernate.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Date surgeryTime;

    @ManyToMany(mappedBy = "surgeries")
    private Set<Doctor> doctors;

    public Surgery(Date surgeryTime) {
        this.surgeryTime = surgeryTime;
    }
}
