package ru.axnikita.praktika.case3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.axnikita.praktika.case3.entity.TravelEntityCase3;

import java.util.List;

public interface TravelRepositoryCase3 extends JpaRepository<TravelEntityCase3, Long> {

    @Query(value = "SELECT * FROM travels b WHERE b.login = ?1", nativeQuery = true)
    List<TravelEntityCase3> findTravelsByLogin(String login);

}
