package ru.axnikita.praktika.model;

import java.util.List;

public record Post(String title, String description, String text, boolean isPublic, List<String> tags) {
}
