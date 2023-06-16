package ru.axnikita.praktika.case1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.axnikita.praktika.case1.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

//    @Query(
//            value = "SELECT * FROM users u WHERE u.login = ?1",
//            nativeQuery = true)
//    PostEntity findUserByLogin(String login);

}
