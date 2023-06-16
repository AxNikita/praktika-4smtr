package ru.axnikita.praktika.case3.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.axnikita.praktika.case3.entity.UserEntityCase3;
import ru.axnikita.praktika.case3.repository.UserRepositoryCase3;
import ru.axnikita.praktika.case1.model.Auth;

@RestController
@RequestMapping("/case3")
public class AuthControllerCase3 {

    private final UserRepositoryCase3 userRepositoryCase3;

    public AuthControllerCase3(UserRepositoryCase3 userRepositoryCase3) {
        this.userRepositoryCase3 = userRepositoryCase3;
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody Auth auth) {

        UserEntityCase3 found = userRepositoryCase3.findUserByLogin(auth.login());
        if (found == null) {
            try {
                UserEntityCase3 userEntityCase3 = new UserEntityCase3();
                userEntityCase3.setLogin(auth.login());
                userEntityCase3.setPassword(auth.password());
                found = userRepositoryCase3.save(userEntityCase3);
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
