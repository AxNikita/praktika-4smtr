package ru.axnikita.praktika.case1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.axnikita.praktika.case1.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(
            value = "SELECT * FROM users u WHERE u.login = ?1",
            nativeQuery = true)
    UserEntity findUserByLogin(String login);

}
