package com.homework.jpahibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Date surgeryTime;

    @ManyToMany(mappedBy = "surgeries", fetch = FetchType.EAGER)
    private Set<Doctor> doctors;
}
