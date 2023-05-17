package ru.axnikita.praktika.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private final String login;

    @Column(name = "password")
    private final String password;

    @Column(name = "subscribers")
    private final List<String> subscribers;
}
