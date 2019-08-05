package com.homework.jpahibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private Specialization specialization;

    @OneToOne
    private DoctorOffice doctorOffice;
    @OneToMany(mappedBy = "familyDoctor", cascade = CascadeType.PERSIST)
    private Set<Patient> patients;
    @ManyToMany
    @JoinTable(name = "surgery_doctors",
                joinColumns = @JoinColumn(name = "doctor_id"),
    inverseJoinColumns = @JoinColumn(name = "surgery_id"))
    private Set<Surgery> surgeries;
}
