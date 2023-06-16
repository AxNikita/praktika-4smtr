package ru.axnikita.praktika.case5.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.axnikita.praktika.case5.entity.UserEntityCase5;
import ru.axnikita.praktika.case5.repository.UserRepositoryCase5;
import ru.axnikita.praktika.case1.model.Auth;

@RestController
@RequestMapping("/case5")
public class AuthControllerCase5 {

    private final UserRepositoryCase5 userRepositoryCase5;

    public AuthControllerCase5(UserRepositoryCase5 userRepositoryCase5) {
        this.userRepositoryCase5 = userRepositoryCase5;
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody Auth auth) {

        if ("admin".equals(auth.login()) && "admin".equals(auth.password())) {
            return ResponseEntity.noContent().build();
        }

        UserEntityCase5 found = userRepositoryCase5.findUserByLogin(auth.login());
        if (found == null) {
            try {
                UserEntityCase5 userEntityCase5 = new UserEntityCase5();
                userEntityCase5.setLogin(auth.login());
                userEntityCase5.setPassword(auth.password());
                userEntityCase5.setType("USER");
                found = userRepositoryCase5.save(userEntityCase5);
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }

        }

        if (!found.getPassword().equals(auth.password())) {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).build();
        }

        return ResponseEntity.ok(found.getType());
    }

}
