package com.homework.jpahibernate;

import com.homework.jpahibernate.model.Doctor;
import com.homework.jpahibernate.model.DoctorOffice;
import com.homework.jpahibernate.model.Patient;
import com.homework.jpahibernate.model.Specialization;
import com.homework.jpahibernate.repository.DoctorOfficeRepository;
import com.homework.jpahibernate.repository.DoctorRepository;
import com.homework.jpahibernate.repository.PatientRepository;
import com.homework.jpahibernate.repository.SurgeryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorOfficeRepository doctorOfficeRepository;
    @Autowired
    private SurgeryRepository surgeryRepository;
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaHibernateApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Patient patient1 = new Patient("Marija Trajkova", "12345");
        Patient patient2 = new Patient("Jon Doe", "09876");
        Patient patient3 = new Patient("Nikola S", "19385");

        Doctor doctor1 = new Doctor("Dr. House", Specialization.SURGEON);
        Doctor doctor2 = new Doctor("Hannibal Lecter", Specialization.PSYCHIATRIST);


        //ONE TO MANY - MANY TO ONE
        doctor1.setPatients(Stream.of(patient1, patient2).collect(Collectors.toSet()));
        doctor2.setPatients(Stream.of(patient3).collect(Collectors.toSet()));
        doctor2.setDoctorOffice(new DoctorOffice("Skopje"));

        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);

        System.out.println(doctorRepository.findById(1L).get().getName());
        doctorRepository.findById(1L).get().getPatients().stream().forEach(patient -> System.out.println(patient.getName()));

        patient1.setFamilyDoctor(doctor1);
        patient2.setFamilyDoctor(doctor1);
        patient3.setFamilyDoctor(doctor2);

        patientRepository.save(patient1);
        patientRepository.save(patient2);
        patientRepository.save(patient3);

        //ONE TO ONE
        DoctorOffice doctorOffice = new DoctorOffice("Bitola");
        doctorOffice.setDoctor(doctor1);
        doctorOfficeRepository.save(doctorOffice);

        doctor1.setDoctorOffice(doctorOffice);
        doctorRepository.save(doctor1);

    }
}
