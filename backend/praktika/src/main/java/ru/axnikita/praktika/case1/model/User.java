package ru.axnikita.praktika.case1.model;

import jakarta.validation.constraints.NotBlank;

public record User(@NotBlank(message = "login should not be empty") String login) {
}
