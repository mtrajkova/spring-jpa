package com.homework.jpahibernate.repository;

import com.homework.jpahibernate.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
