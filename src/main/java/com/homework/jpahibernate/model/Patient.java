package com.homework.jpahibernate.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "patient")
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
