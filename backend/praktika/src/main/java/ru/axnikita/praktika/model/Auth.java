package ru.axnikita.praktika.model;

import jakarta.validation.constraints.NotBlank;

public record Auth(
        @NotBlank(message = "login should not be empty") String login,
        @NotBlank(message = "login should not be empty") String password
) {
}
