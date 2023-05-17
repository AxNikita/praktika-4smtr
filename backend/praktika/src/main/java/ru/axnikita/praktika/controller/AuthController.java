package ru.axnikita.praktika.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.axnikita.praktika.model.Auth;
import ru.axnikita.praktika.model.UserEntity;
import ru.axnikita.praktika.repository.UserRepository;

import java.util.List;

@RestController
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@Valid @RequestBody Auth auth) {
        UserEntity found = userRepository.findUserByLogin(auth.login());
        if (found == null) {
            try {
                UserEntity userEntity = new UserEntity();
                userEntity.setLogin(auth.login());
                userEntity.setPassword(auth.password());
                userEntity.setSubscribers(List.of());

                found = userRepository.save(userEntity);
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }

        }

        if (!found.getPassword().equals(auth.password())) {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).build();
        }

        return ResponseEntity.ok().build();
    }

}
