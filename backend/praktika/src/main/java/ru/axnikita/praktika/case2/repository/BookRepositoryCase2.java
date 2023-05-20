package ru.axnikita.praktika.case2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.axnikita.praktika.case2.entity.BookEntityCase2;

import java.util.List;

public interface BookRepositoryCase2 extends JpaRepository<BookEntityCase2, Long> {

    @Query(value = "SELECT * FROM books b WHERE b.login = ?1", nativeQuery = true)
    List<BookEntityCase2> findBooksByLogin(String login);

}
