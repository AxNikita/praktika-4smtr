package ru.axnikita.praktika.case5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.axnikita.praktika.case5.entity.PatientCardEntityCase5;

public interface PatientCardRepositoryCase5 extends JpaRepository<PatientCardEntityCase5, Long> {

    @Query(
            value = "SELECT * FROM patientcards p WHERE p.patient = ?1",
            nativeQuery = true)
    PatientCardEntityCase5 findCardByPatientId(Long patientId);

    @Query(
            value = "SELECT * FROM patientcards p WHERE p.doctor = ?1",
            nativeQuery = true)
    PatientCardEntityCase5 findCardByDoctorId(Long doctorId);

}
