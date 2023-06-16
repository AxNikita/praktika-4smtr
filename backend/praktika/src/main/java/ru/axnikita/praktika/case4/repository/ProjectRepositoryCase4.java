package ru.axnikita.praktika.case4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.axnikita.praktika.case4.entity.ProjectEntityCase4;

public interface ProjectRepositoryCase4 extends JpaRepository<ProjectEntityCase4, Long> {

//    @Query(value = "SELECT * FROM projects p WHERE p.login = ?1", nativeQuery = true)
//    List<ProjectEntityCase4> findTravelsByLogin(String login);

}
