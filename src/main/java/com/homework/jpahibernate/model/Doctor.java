package com.homework.jpahibernate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private Specialization specialization;

    @OneToOne(cascade = CascadeType.PERSIST)
    private DoctorOffice doctorOffice;

    @OneToMany(mappedBy = "familyDoctor", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Patient> patients;

    @ManyToMany
    @JoinTable(name = "surgery_doctors",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "surgery_id"))
    private Set<Surgery> surgeries = new HashSet<>();

    public Doctor(String name, Specialization specialization) {
        this.name = name;
        this.specialization = specialization;
    }

}
