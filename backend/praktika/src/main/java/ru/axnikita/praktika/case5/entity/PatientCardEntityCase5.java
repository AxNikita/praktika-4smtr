package ru.axnikita.praktika.case5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "patientcard")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public class PatientCardEntityCase5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient")
    private Long patient;

    @Column(name = "doctor")
    private Long doctor;

    @Column(name = "service")
    private String service;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private String date;

    @Column(name = "status")
    private String status;

    public PatientCardEntityCase5 baseOnAddId(PatientCardEntityCase5 entity, long id) {
        this.id = id;
        this.patient = entity.getPatient();
        this.doctor = entity.getDoctor();
        this.service = entity.getService();
        this.description = entity.getDescription();
        this.date = entity.getDate();
        this.status = entity.getStatus();
        return this;
    }
}
