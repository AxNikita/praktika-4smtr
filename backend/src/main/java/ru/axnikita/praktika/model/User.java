package ru.axnikita.praktika.model;

import java.util.List;

public record User(String login, String password, List<String> subscribers) {
}
