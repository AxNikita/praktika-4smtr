package ru.axnikita.praktika.model;

import java.util.List;

public record Post(String login, String id, String title, String description, String text, boolean isPublic, List<Tag> tags) {
}
