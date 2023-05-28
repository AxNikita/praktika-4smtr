package ru.axnikita.praktika.case2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.axnikita.praktika.case2.model.BookCategory;

import java.util.List;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public class BookEntityCase2 {
    // title
    // description
    // категория
    // автор
    // год написания
    // цена аренды
    // цена покупки
    // статус (PUBLIC, PRIVATE) (для регулировки видимости книги у пользователей)
    // доступность (availability) (AVAILABLE, RESERVE, BUY) (если RESERVE/BUY - книга кем-то зарезервирована, и поля описаные ниже будут не null, а заполнены)
    // зарезервирована_кем (login) (пользователь который купил, зарезервировал)
    // зарезервирован_до (reserved_until)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private List<BookCategory> category;

    @Column(name = "author")
    private String author;

    @Column(name = "date")
    private String date;

    @Column(name = "priceBuy")
    private String priceBuy;

    @Column(name = "priceReserve")
    private String priceReserve;

    @Column(name = "status")
    private BookStatusCase2 status;

    @Column(name = "availability")
    private BookAvailabilityCase2 availability;

    @Column(name = "login")
    private String login;

    @Column(name = "reservedUntil")
    private String reservedUntil;

    public static BookEntityCase2 defaultBookOf(String title, String description, List<BookCategory> category, String author, String date, String priceBuy, String priceReserve) {
        BookEntityCase2 entity = new BookEntityCase2();
        entity.setTitle(title);
        entity.setDescription(description);
        entity.setCategory(category);
        entity.setAuthor(author);
        entity.setDate(date);
        entity.setPriceBuy(priceBuy);
        entity.setPriceReserve(priceReserve);
        entity.setStatus(BookStatusCase2.PUBLIC);
        entity.setAvailability(BookAvailabilityCase2.AVAILABLE);
        return entity;
    }

    public BookEntityCase2 baseOnAddId(BookEntityCase2 entity, long id) {
        this.id = id;
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.category = entity.getCategory();
        this.author = entity.getAuthor();
        this.date = entity.getDate();
        this.priceBuy = entity.getPriceBuy();
        this.priceReserve = entity.getPriceReserve();
        this.status = entity.getStatus();
        this.availability = entity.getAvailability();
        this.login = entity.getLogin();
        this.reservedUntil = entity.getReservedUntil();
        return this;
    }
}
