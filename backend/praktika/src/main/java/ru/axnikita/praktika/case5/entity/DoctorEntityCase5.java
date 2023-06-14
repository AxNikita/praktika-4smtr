package ru.axnikita.praktika.case5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public class DoctorEntityCase5 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    private String type;

    // =================================================

    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "services")
    private List<String> services;
}
