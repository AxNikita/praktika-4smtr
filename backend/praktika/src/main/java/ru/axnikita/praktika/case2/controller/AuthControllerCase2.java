package ru.axnikita.praktika.case2.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.axnikita.praktika.case2.entity.UserEntityCase2;
import ru.axnikita.praktika.case2.repository.UserRepositoryCase2;
import ru.axnikita.praktika.model.Auth;

@RestController
@RequestMapping("/case2")
public class AuthControllerCase2 {

    private final UserRepositoryCase2 userRepositoryCase2;

    public AuthControllerCase2(UserRepositoryCase2 userRepositoryCase2) {
        this.userRepositoryCase2 = userRepositoryCase2;
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody Auth auth) {

        if ("admin".equals(auth.login()) && "admin".equals(auth.password())) {
            return ResponseEntity.noContent().build();
        }

        UserEntityCase2 found = userRepositoryCase2.findUserByLogin(auth.login());
        if (found == null) {
            try {
                UserEntityCase2 userEntityCase2 = new UserEntityCase2();
                userEntityCase2.setLogin(auth.login());
                userEntityCase2.setPassword(auth.password());
                found = userRepositoryCase2.save(userEntityCase2);
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
