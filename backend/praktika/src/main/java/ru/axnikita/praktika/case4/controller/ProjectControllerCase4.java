package ru.axnikita.praktika.case4.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.axnikita.praktika.case4.entity.ProjectEntityCase4;
import ru.axnikita.praktika.case4.repository.ProjectRepositoryCase4;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/case4")
public class ProjectControllerCase4 {

    private final ProjectRepositoryCase4 projectRepository;

    public ProjectControllerCase4(ProjectRepositoryCase4 projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/project")
    public ResponseEntity<ProjectEntityCase4> getProjectById(@RequestParam long id) {
        try {
            Optional<ProjectEntityCase4> found = projectRepository.findById(id);
            if (found.isEmpty()) {
                return ResponseEntity.internalServerError().build();
            }
            return ResponseEntity.ok(found.get());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/project")
    public ResponseEntity<ProjectEntityCase4> addProject(@RequestBody ProjectEntityCase4 travelEntity) {
        try {
            projectRepository.save(travelEntity);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/project")
    public ResponseEntity<ProjectEntityCase4> editProjectById(@RequestParam long id,
                                                              @RequestBody ProjectEntityCase4 travelEntity) {
        try {
            Optional<ProjectEntityCase4> found = projectRepository.findById(id);
            found.ifPresent(entity -> projectRepository.save(entity.baseOnAddId(travelEntity, entity.getId())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/project")
    public ResponseEntity<String> deleteProjectById(@RequestParam long id) {
        try {
            projectRepository.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectEntityCase4>> getAllProjects() {

        List<ProjectEntityCase4> allTravels = projectRepository.findAll();

        return ResponseEntity.ok(allTravels);
    }

}
