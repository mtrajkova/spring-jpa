package com.homework.jpahibernate.repository;

import com.homework.jpahibernate.model.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurgeryRepository extends JpaRepository<Surgery, Long> {
}
