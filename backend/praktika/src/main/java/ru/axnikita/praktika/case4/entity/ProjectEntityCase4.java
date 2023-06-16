package ru.axnikita.praktika.case4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public class ProjectEntityCase4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "users")
    private List<String> users;


    public ProjectEntityCase4 baseOnAddId(ProjectEntityCase4 entity, long id) {
        this.id = id;
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.users = entity.getUsers();
        this.status = entity.getStatus();
        return this;
    }
}
