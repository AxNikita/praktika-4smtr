package ru.axnikita.praktika.case1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.axnikita.praktika.case1.model.User;
import ru.axnikita.praktika.case1.repository.UserRepository;
import ru.axnikita.praktika.case1.entity.UserEntity;

import java.util.List;

@RestController
public class SubscribeController {

    private final UserRepository userRepository;

    public SubscribeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestParam String login,
                                            @RequestBody User user) {

        try {
            UserEntity poster = userRepository.findUserByLogin(user.login());
            poster.getSubscribers().add(login);
            userRepository.save(poster);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/subscribers")
    public ResponseEntity<List<String>> getSubscribers(@RequestParam String login) {

        try {
            UserEntity user = userRepository.findUserByLogin(login);
            return ResponseEntity.ok(user.getSubscribers());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

}
