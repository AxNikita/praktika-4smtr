package ru.axnikita.praktika.case1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.axnikita.praktika.case1.entity.CommentEntity;
import ru.axnikita.praktika.case1.repository.CommentRepository;

import java.util.List;

@RestController
public class CommentController {

    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PostMapping("/comment")
    public ResponseEntity<String> createComment(@RequestParam String login,
                                                @RequestBody CommentEntity commentEntity) {

        try {
            commentEntity.setLogin(login);
            commentRepository.save(commentEntity);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/comment")
    public ResponseEntity<List<CommentEntity>> getComments(@RequestParam String login,
                                              @RequestParam String postId) {

        try {
            List<CommentEntity> allUserByPostId = commentRepository.findAllUserByPostId(postId);
            return ResponseEntity.ok(allUserByPostId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

}
