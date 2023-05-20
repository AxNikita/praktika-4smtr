package ru.axnikita.praktika.case2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.axnikita.praktika.case2.entity.BookAvailabilityCase2;
import ru.axnikita.praktika.case2.entity.BookEntityCase2;
import ru.axnikita.praktika.case2.entity.BookStatusCase2;
import ru.axnikita.praktika.case2.repository.BookRepositoryCase2;

import java.util.List;

@RestController
@RequestMapping("/case2")
public class BookControllerCase2 {

    private final BookRepositoryCase2 bookRepository;

    public BookControllerCase2(BookRepositoryCase2 bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/db-init")
    public ResponseEntity<String> initBookDB() {
        try {
            bookRepository.save(BookEntityCase2.defaultBookOf("Java Book", "Some description", "action", "Nik", "10.02.2001", "200 руб.", "100 руб."));
            bookRepository.save(BookEntityCase2.defaultBookOf("Javascript Book", "Some description", "action", "Nik", "10.02.2001", "300 руб.", "200 руб."));
            bookRepository.save(BookEntityCase2.defaultBookOf("Pascal Book", "Some description", "action", "Nik", "10.02.2001", "400 руб.", "300 руб."));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookEntityCase2>> getAllBook() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @PostMapping("/book")
    public ResponseEntity<String> creteOrUpdateBook(BookEntityCase2 bookEntity) {
        try {
            bookRepository.save(bookEntity);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/book")
    public ResponseEntity<String> deleteBook(BookEntityCase2 bookEntity) {
        try {
            bookRepository.deleteById(bookEntity.getId());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user-books")
    public ResponseEntity<List<BookEntityCase2>> getAllBook(@RequestParam(required = false) String login) {

        List<BookEntityCase2> books;

        if (!login.isBlank()) {
            books = bookRepository.findBooksByLogin(login);
        } else {
            books = bookRepository.findAll().stream()
                    .filter(book -> book.getStatus().equals(BookStatusCase2.PUBLIC))
                    .filter(book -> book.getAvailability().equals(BookAvailabilityCase2.AVAILABLE))
                    .toList();
        }

        return ResponseEntity.ok(books);
    }

}
