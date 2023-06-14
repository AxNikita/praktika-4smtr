package ru.axnikita.praktika.case5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.axnikita.praktika.case5.entity.UserEntityCase5;

public interface UserRepositoryCase5 extends JpaRepository<UserEntityCase5, Long> {

    @Query(
            value = "SELECT * FROM users5 u WHERE u.login = ?1",
            nativeQuery = true)
    UserEntityCase5 findUserByLogin(String login);

}
