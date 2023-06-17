package ru.axnikita.praktika.case5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.axnikita.praktika.case3.entity.TravelEntityCase3;
import ru.axnikita.praktika.case5.entity.PatientCardEntityCase5;
import ru.axnikita.praktika.case5.repository.PatientCardRepositoryCase5;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/case5")
public class PatientCardControllerCase5 {

    private final PatientCardRepositoryCase5 patientCardRepositoryCase5;

    public PatientCardControllerCase5(PatientCardRepositoryCase5 patientCardRepositoryCase5) {
        this.patientCardRepositoryCase5 = patientCardRepositoryCase5;
    }

    @GetMapping("/patient-card-doctor")
    public ResponseEntity<List<PatientCardEntityCase5>> findAllCardsByDoctor(@RequestParam String doctor) {

        List<PatientCardEntityCase5> found = patientCardRepositoryCase5.findAll();

        List<PatientCardEntityCase5> list = found.stream().filter(card -> card.getDoctor().equals(doctor)).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/patient-card-patient")
    public ResponseEntity<List<PatientCardEntityCase5>> findAllCardsByPatient(@RequestParam String patient) {

        List<PatientCardEntityCase5> found = patientCardRepositoryCase5.findAll();

        List<PatientCardEntityCase5> list = found.stream().filter(card -> card.getPatient().equals(patient)).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/patient-cards")
    public ResponseEntity<List<PatientCardEntityCase5>> findAllCards() {

        List<PatientCardEntityCase5> found = patientCardRepositoryCase5.findAll();

        return ResponseEntity.ok(found);
    }

    @PostMapping("/patient-card")
    public ResponseEntity<String> createCard(@RequestBody PatientCardEntityCase5 patientCardEntityCase5) {
        try {
            patientCardRepositoryCase5.save(patientCardEntityCase5);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }


    @PutMapping("/patient-card")
    public ResponseEntity<TravelEntityCase3> editCardById(@RequestParam long id,
                                                          @RequestBody PatientCardEntityCase5 patientCardEntityCase5) {
        try {
            Optional<PatientCardEntityCase5> found = patientCardRepositoryCase5.findById(id);
            found.ifPresent(entity -> patientCardRepositoryCase5.save(entity.baseOnAddId(patientCardEntityCase5, entity.getId())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/patient-card")
    public ResponseEntity<String> deleteCardById(@RequestParam long id) {
        try {
            patientCardRepositoryCase5.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

}
