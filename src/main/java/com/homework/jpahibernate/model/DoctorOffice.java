package com.homework.jpahibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String location;

    public DoctorOffice(String location) {
        this.location = location;
    }

    @OneToOne(mappedBy = "doctorOffice")
    private Doctor doctor;
}
