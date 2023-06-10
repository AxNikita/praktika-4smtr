package ru.axnikita.praktika.case3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.axnikita.praktika.case3.entity.UserEntityCase3;

public interface UserRepositoryCase3 extends JpaRepository<UserEntityCase3, Long> {

    @Query(
            value = "SELECT * FROM users3 u WHERE u.login = ?1",
            nativeQuery = true)
    UserEntityCase3 findUserByLogin(String login);

}
