package ru.axnikita.praktika.case4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.axnikita.praktika.case4.entity.UserEntityCase4;

public interface UserRepositoryCase4 extends JpaRepository<UserEntityCase4, Long> {

    @Query(
            value = "SELECT * FROM users4 u WHERE u.login = ?1",
            nativeQuery = true)
    UserEntityCase4 findUserByLogin(String login);

}
