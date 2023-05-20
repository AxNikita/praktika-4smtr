package ru.axnikita.praktika.model;

import jakarta.validation.constraints.NotBlank;

public record Auth(String login, String password) {
}
