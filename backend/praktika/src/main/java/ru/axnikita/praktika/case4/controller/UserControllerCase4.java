package ru.axnikita.praktika.case4.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.axnikita.praktika.case4.entity.UserEntityCase4;
import ru.axnikita.praktika.case4.repository.UserRepositoryCase4;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/case4")
public class UserControllerCase4 {

    private final UserRepositoryCase4 userRepositoryCase4;

    public UserControllerCase4(UserRepositoryCase4 userRepositoryCase4) {
        this.userRepositoryCase4 = userRepositoryCase4;
    }

    @GetMapping("/user")
    public ResponseEntity<UserEntityCase4> getUserById(@RequestParam long id) {
        try {
            Optional<UserEntityCase4> found = userRepositoryCase4.findById(id);
            if (found.isEmpty()) {
                return ResponseEntity.internalServerError().build();
            }
            return ResponseEntity.ok(found.get());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/user")
    public ResponseEntity<UserEntityCase4> addUser(@RequestBody UserEntityCase4 userEntity) {
        try {
            userRepositoryCase4.save(userEntity);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user")
    public ResponseEntity<UserEntityCase4> editUserById(@RequestParam long id,
                                                        @RequestBody UserEntityCase4 userEntity) {
        try {
            Optional<UserEntityCase4> found = userRepositoryCase4.findById(id);
            found.ifPresent(entity -> userRepositoryCase4.save(entity.baseOnAddId(userEntity, entity.getId())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUserById(@RequestParam long id) {
        try {
            userRepositoryCase4.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserEntityCase4>> getAllUsers() {

        List<UserEntityCase4> allTravels = userRepositoryCase4.findAll();

        return ResponseEntity.ok(allTravels);
    }

}
