package ru.axnikita.praktika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.axnikita.praktika.entity.CommentEntity;
import ru.axnikita.praktika.entity.PostEntity;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query(
            value = "SELECT * FROM comments c WHERE c.post = ?1",
            nativeQuery = true)
    List<CommentEntity> findAllUserByPostId(String postId);

}
