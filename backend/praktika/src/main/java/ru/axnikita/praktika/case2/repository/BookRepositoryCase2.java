package ru.axnikita.praktika.case2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.axnikita.praktika.case2.entity.BookEntityCase2;

public interface BookRepositoryCase2 extends JpaRepository<BookEntityCase2, Long> {

//    @Query(
//            value = "SELECT * FROM users2 u WHERE u.login = ?1",
//            nativeQuery = true)
//    UserEntity findUserByLogin(String login);

}
