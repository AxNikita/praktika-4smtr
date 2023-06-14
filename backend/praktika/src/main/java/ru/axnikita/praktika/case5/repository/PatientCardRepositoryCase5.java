package ru.axnikita.praktika.case5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.axnikita.praktika.case5.entity.PatientCardEntityCase5;

public interface PatientCardRepositoryCase5 extends JpaRepository<PatientCardEntityCase5, Long> {

    @Query(
            value = "SELECT * FROM patientcard u WHERE u.login = ?1",
            nativeQuery = true)
    PatientCardEntityCase5 findCardByLogin(String login);

}
