package ru.axnikita.praktika.case3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.axnikita.praktika.case3.entity.TravelEntityCase3;
import ru.axnikita.praktika.case3.model.Rating;
import ru.axnikita.praktika.case3.repository.TravelRepositoryCase3;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/case3")
public class TravelControllerCase3 {

    private final TravelRepositoryCase3 travelRepository;

    public TravelControllerCase3(TravelRepositoryCase3 travelRepository) {
        this.travelRepository = travelRepository;
    }

    @GetMapping("/db-init")
    public ResponseEntity<String> initBookDB() {
        try {
            travelRepository.save(TravelEntityCase3.defaultTravelOf("user1", "Egypt", "egregegeegerg",
                    List.of("rewrwe", "ergergerg"), "http://localhost:/eger", 100,
                    new Rating(5, 5, 5, 5), List.of("4864", "dvav"),
                    List.of("sadasdasd", "dfvsfsdf")));

            travelRepository.save(TravelEntityCase3.defaultTravelOf("user2", "Russia", "egregegeegerg",
                    List.of("rewrwe", "ergergerg"), "http://localhost:/eger", 100000000,
                    new Rating(5, 5, 5, 5), List.of("4864", "dvav"),
                    List.of("sadasdasd", "dfvsfsdf")));

            travelRepository.save(TravelEntityCase3.defaultTravelOf("user2", "Kazakhstan", "egregegeegerg",
                    List.of("rewrwe", "ergergerg"), "http://localhost:/eger", 100555,
                    new Rating(5, 5, 5, 5), List.of("4864", "dvav"),
                    List.of("sadasdasd", "dfvsfsdf")));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping("/travel")
    public ResponseEntity<TravelEntityCase3> getTravelById(@RequestParam long id) {
        try {
            Optional<TravelEntityCase3> found = travelRepository.findById(id);
            if (found.isEmpty()) {
                return ResponseEntity.internalServerError().build();
            }
            return ResponseEntity.ok(found.get());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/travel")
    public ResponseEntity<TravelEntityCase3> addTravel(@RequestBody TravelEntityCase3 travelEntity) {
        try {
            travelRepository.save(travelEntity);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/travel")
    public ResponseEntity<TravelEntityCase3> editTravelById(@RequestParam long id,
                                                            @RequestBody TravelEntityCase3 travelEntity) {
        try {
            Optional<TravelEntityCase3> found = travelRepository.findById(id);
            found.ifPresent(entity -> travelRepository.save(entity.baseOnAddId(travelEntity, entity.getId())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/travel")
    public ResponseEntity<String> deleteTravelById(@RequestParam long id) {
        try {
            travelRepository.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/travels")
    public ResponseEntity<List<TravelEntityCase3>> getAllTravels(@RequestParam(required = false) String author) {

        List<TravelEntityCase3> allTravels = travelRepository.findAll();

        if (author != null) {
            return ResponseEntity.ok(allTravels.stream().filter(entity -> entity.getAuthor().equals(author)).toList());
        }

        return ResponseEntity.ok(allTravels);
    }

}
