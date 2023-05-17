package ru.axnikita.praktika.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.axnikita.praktika.model.User;
import ru.axnikita.praktika.repository.UserRepository;

import java.util.List;

@RestController
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody User user) {
        User found = userRepository.findUserByLogin(user.getLogin());
        if (found == null) {
            // create
            // Or error creating - 500 http code
            try {
                found = userRepository.save(user);
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }

        }

        if (!found.getPassword().equals(user.getPassword())) {
            // Error validation
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).build();
        }

        return ResponseEntity.ok().build();
    }


    @GetMapping("/admin/auth")
    public ResponseEntity<List<User>> auth() {
        List<User> all = userRepository.findAll();
        return ResponseEntity.ok(all);
    }

}
