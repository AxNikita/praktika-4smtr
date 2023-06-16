package ru.axnikita.praktika.case1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.axnikita.praktika.case1.repository.UserRepository;
import ru.axnikita.praktika.case1.entity.UserEntity;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> auth() {
        List<UserEntity> all = userRepository.findAll();
        return ResponseEntity.ok(all);
    }

}
