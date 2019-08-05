package com.homework.jpahibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String SSN;

    @ManyToOne
    private Doctor familyDoctor;

    public Patient(String name, String SSN) {
        this.name = name;
        this.SSN = SSN;
    }
}
