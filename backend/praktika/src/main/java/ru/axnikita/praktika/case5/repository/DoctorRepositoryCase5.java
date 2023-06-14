package ru.axnikita.praktika.case5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.axnikita.praktika.case5.entity.DoctorEntityCase5;

public interface DoctorRepositoryCase5 extends JpaRepository<DoctorEntityCase5, Long> {

    @Query(
            value = "SELECT * FROM doctors u WHERE u.login = ?1",
            nativeQuery = true)
    DoctorEntityCase5 findDoctorByLogin(String login);

}
