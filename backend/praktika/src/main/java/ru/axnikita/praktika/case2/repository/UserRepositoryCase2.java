package ru.axnikita.praktika.case2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.axnikita.praktika.case2.entity.UserEntityCase2;

public interface UserRepositoryCase2 extends JpaRepository<UserEntityCase2, Long> {

    @Query(
            value = "SELECT * FROM users2 u WHERE u.login = ?1",
            nativeQuery = true)
    UserEntityCase2 findUserByLogin(String login);

}
