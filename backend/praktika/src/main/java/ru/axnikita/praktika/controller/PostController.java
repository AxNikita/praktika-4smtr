package ru.axnikita.praktika.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.axnikita.praktika.entity.PostEntity;
import ru.axnikita.praktika.model.PostFilter;
import ru.axnikita.praktika.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping("/post")
    public ResponseEntity<String> createPost(@RequestParam String login,
                                             @RequestBody PostEntity postEntity) {

        try {
            postEntity.setLogin(login);
            postRepository.save(postEntity);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/post")
    public ResponseEntity<String> updatePost(@RequestParam String login,
                                             @RequestParam String postId,
                                             @RequestBody PostEntity postEntity) {

        try {
            Optional<PostEntity> found = postRepository.findById(Long.valueOf(postId));
            found.ifPresent(entity -> postRepository.save(entity.baseOn(postEntity)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/post")
    public ResponseEntity<String> deletePost(@RequestParam String login,
                                             @RequestParam String postId) {

        try {
            postRepository.deleteById(Long.valueOf(postId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/post")
    public ResponseEntity<List<PostEntity>> getPosts(@RequestBody PostFilter postFilter) {

        try {
            List<PostEntity> postsByPublic = postRepository
                    .findAll()
                    .stream()
                    .filter(entity -> String.valueOf(entity.isPublic()).equals(String.valueOf(postFilter.isPublic()))).toList();
            return ResponseEntity.ok(postsByPublic);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/user-posts")
    public ResponseEntity<List<PostEntity>> getPostsByUser(@RequestParam String login) {

        try {
            List<PostEntity> postsByPublic = postRepository
                    .findAll()
                    .stream()
                    .filter(entity -> entity.getLogin().equals(login)).toList();
            return ResponseEntity.ok(postsByPublic);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }


}
