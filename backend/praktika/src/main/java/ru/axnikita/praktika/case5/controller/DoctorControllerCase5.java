package ru.axnikita.praktika.case5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.axnikita.praktika.case5.entity.DoctorEntityCase5;
import ru.axnikita.praktika.case5.entity.UserEntityCase5;
import ru.axnikita.praktika.case5.repository.DoctorRepositoryCase5;
import ru.axnikita.praktika.case5.repository.UserRepositoryCase5;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/case5")
public class DoctorControllerCase5 {

    private final UserRepositoryCase5 userRepositoryCase5;
    private final DoctorRepositoryCase5 doctorRepositoryCase5;

    public DoctorControllerCase5(UserRepositoryCase5 userRepositoryCase5, DoctorRepositoryCase5 doctorRepositoryCase5) {
        this.userRepositoryCase5 = userRepositoryCase5;
        this.doctorRepositoryCase5 = doctorRepositoryCase5;
    }

    @GetMapping("/doctor")
    public ResponseEntity<DoctorEntityCase5> findDoctorById(@RequestParam long id) {

        Optional<DoctorEntityCase5> found = doctorRepositoryCase5.findById(id);
        if (found.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(found.get());
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorEntityCase5>> findDoctors() {

        List<DoctorEntityCase5> found = doctorRepositoryCase5.findAll();

        return ResponseEntity.ok(found);
    }

    @PostMapping("/doctor")
    public ResponseEntity<String> createDoctor(@RequestBody DoctorEntityCase5 doctorEntityCase5) {


        try {
            UserEntityCase5 userEntityCase5 = new UserEntityCase5();
            userEntityCase5.setLogin(doctorEntityCase5.getLogin());
            userEntityCase5.setPassword(doctorEntityCase5.getPassword());
            userEntityCase5.setType("DOCTOR");
            userRepositoryCase5.save(userEntityCase5);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        try {
            DoctorEntityCase5 entity = new DoctorEntityCase5();
            entity.setLogin(doctorEntityCase5.getLogin());
            entity.setPassword(doctorEntityCase5.getPassword());
            entity.setType("DOCTOR");
            entity.setName(doctorEntityCase5.getName());
            entity.setDepartment(doctorEntityCase5.getDepartment());
            entity.setQualification(doctorEntityCase5.getQualification());
            entity.setServices(doctorEntityCase5.getServices());
            doctorRepositoryCase5.save(entity);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }


    @PutMapping("/doctor")
    public ResponseEntity<String> updateDoctor(@RequestBody DoctorEntityCase5 doctorEntityCase5) {


        UserEntityCase5 userFound = userRepositoryCase5.findUserByLogin(doctorEntityCase5.getLogin());
        if (userFound != null) {
            try {
                userFound.setLogin(doctorEntityCase5.getLogin());
                userFound.setPassword(doctorEntityCase5.getPassword());
                userRepositoryCase5.save(userFound);
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }
        }

        DoctorEntityCase5 doctorFound = doctorRepositoryCase5.findDoctorByLogin(doctorEntityCase5.getLogin());
        if (doctorFound != null) {
            try {
                doctorFound.setLogin(doctorEntityCase5.getLogin());
                doctorFound.setPassword(doctorEntityCase5.getPassword());
                doctorFound.setName(doctorEntityCase5.getName());
                doctorFound.setDepartment(doctorEntityCase5.getDepartment());
                doctorFound.setQualification(doctorEntityCase5.getQualification());
                doctorFound.setServices(doctorEntityCase5.getServices());
                doctorRepositoryCase5.save(doctorFound);
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/doctor")
    public ResponseEntity<DoctorEntityCase5> deleteDoctorById(@RequestParam long id) {

        try {
            doctorRepositoryCase5.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }


}
