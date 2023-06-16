package ru.axnikita.praktika.case4.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.axnikita.praktika.case1.model.Auth;
import ru.axnikita.praktika.case4.entity.UserEntityCase4;
import ru.axnikita.praktika.case4.repository.UserRepositoryCase4;

@RestController
@RequestMapping("/case4")
public class AuthControllerCase4 {

    private final UserRepositoryCase4 userRepositoryCase4;

    public AuthControllerCase4(UserRepositoryCase4 userRepositoryCase4) {
        this.userRepositoryCase4 = userRepositoryCase4;
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody Auth auth) {

        if ("admin".equals(auth.login()) && "admin".equals(auth.password())) {
            return ResponseEntity.noContent().build();
        }

        UserEntityCase4 found = userRepositoryCase4.findUserByLogin(auth.login());
        if (found == null) {
            return ResponseEntity.internalServerError().build();
        }

        if (!found.getPassword().equals(auth.password())) {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).build();
        }

        return ResponseEntity.ok().build();
    }

}
