package com.homework.jpahibernate.repository;

import com.homework.jpahibernate.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
