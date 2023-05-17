package ru.axnikita.praktika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.axnikita.praktika.model.User;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            value = "SELECT * FROM users u WHERE u.login = ?1",
            nativeQuery = true)
    User findUserByLogin(String login);

}
