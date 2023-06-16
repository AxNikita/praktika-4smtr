package ru.axnikita.praktika.case4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users4")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public class UserEntityCase4 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "projects")
    private List<String> projects;

    public UserEntityCase4 baseOnAddId(UserEntityCase4 entity, long id) {
        this.id = id;
        this.login = entity.getLogin();
        this.password = entity.getPassword();
        this.projects = entity.getProjects();
        return this;
    }
}
