package ru.axnikita.praktika.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.axnikita.praktika.model.Tag;

import java.util.List;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public final class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "text")
    private String text;

    @Column(name = "is_public")
    @JsonProperty("isPublic")
    private boolean isPublic;

    @Column(name = "tags")
    private List<Tag> tags;

    public PostEntity baseOn(PostEntity entity) {
        this.id = entity.getId();
        this.login = entity.getLogin();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.text = entity.getText();
        this.isPublic = entity.isPublic();
        this.tags = entity.getTags();
        return this;
    }
}
