package com.homework.jpahibernate;

import com.homework.jpahibernate.model.*;
import com.homework.jpahibernate.repository.DoctorOfficeRepository;
import com.homework.jpahibernate.repository.DoctorRepository;
import com.homework.jpahibernate.repository.PatientRepository;
import com.homework.jpahibernate.repository.SurgeryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.Date;
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

        System.out.println("TESTING DOCTOR");
        System.out.println("Doctor name: " + doctorRepository.findById(doctor1.getId()).get().getName() + "\n");

        System.out.println("TESTING ONE TO MANY RELATIONSHIP -> Dr. House's patients");
        doctorRepository.findById(doctor1.getId()).get().getPatients().stream().forEach(patient -> System.out.println("Patient name: " + patient.getName()));

        patient1.setFamilyDoctor(doctor1);
        patient2.setFamilyDoctor(doctor1);
        patient3.setFamilyDoctor(doctor2);

        patientRepository.save(patient1);
        patientRepository.save(patient2);
        patientRepository.save(patient3);

        System.out.println("\nTESTING PATIENT");
        System.out.println("Patient SSN: " + patientRepository.findById(patient2.getId()).get().getSSN() + "\n");

        System.out.println("TESTING MANY TO ONE RELATIONSHIP -> Doctor of patient Jon Doe");
        System.out.println("Patient's doctor name: " + patientRepository.findById(patient2.getId()).get().getFamilyDoctor().getName() + "\n");

        //ONE TO ONE
        DoctorOffice doctorOffice = new DoctorOffice("Bitola");
        doctorOffice.setDoctor(doctor1);
        doctorOfficeRepository.save(doctorOffice);

        doctor1.setDoctorOffice(doctorOffice);
        doctorRepository.save(doctor1);

        System.out.println("TESTING DOCTOR OFFICE");
        System.out.println("Doctor office location: " + doctorOfficeRepository.findById(doctorOffice.getId()).get().getLocation() + "\n");

        System.out.println("TESTING ONE TO ONE RELATIONSHIP - DOCTOR OFFICE SIDE -> Doctor of the office in Bitola");
        System.out.println(doctorOfficeRepository.findById(doctorOffice.getId()).get().getDoctor().getName() + "\n");

        System.out.println("TESTING ONE TO ONE RELATIONSHIP - DOCTOR SIDE -> Office's location of doctor Hannibal Lecter");
        System.out.println(doctorRepository.findById(doctor2.getId()).get().getDoctorOffice().getLocation() + "\n");

        //MANY TO MANY
        Surgery surgery = new Surgery(new Date());
        surgeryRepository.save(surgery);

        System.out.println("TESTING SURGERY");
        System.out.println("Surgery time: " + surgeryRepository.findById(surgery.getId()).get().getSurgeryTime() + "\n");

        surgery.setDoctors(Stream.of(doctor1, doctor2).collect(Collectors.toSet()));
        surgeryRepository.save(surgery);

        doctor1.getSurgeries().add(surgery);
        doctor2.getSurgeries().add(surgery);
        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);

        System.out.println("TESTING MANY TO MANY - SURGERY SIDE -> Doctors of the surgery");
        surgeryRepository.findById(surgery.getId()).get().getDoctors().stream().forEach(doctor -> System.out.println("Doctor name: " + doctor.getName()));

        System.out.println("TESTING MANY TO MANY - DOCTOR SIDE -> Surgeries for Dr. House");
        doctorRepository.findById(doctor1.getId()).get().getSurgeries().stream().forEach(surgery1 -> System.out.println("Surgery id: " + surgery.getId()));
    }
}
