package ru.axnikita.praktika.case2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.axnikita.praktika.case2.entity.BookAvailabilityCase2;
import ru.axnikita.praktika.case2.entity.BookEntityCase2;
import ru.axnikita.praktika.case2.model.BookCategory;
import ru.axnikita.praktika.case2.repository.BookRepositoryCase2;

import java.util.List;
import java.util.Optional;

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
            bookRepository.save(BookEntityCase2.defaultBookOf("Java Book", "Some description", List.of(BookCategory.documentation, BookCategory.balalar), "Nik", "10.02.2001", "200 руб.", "100 руб."));
            bookRepository.save(BookEntityCase2.defaultBookOf("Javascript Book", "Some description", List.of(BookCategory.classic, BookCategory.fantasy), "Nik", "10.02.2001", "300 руб.", "200 руб."));
            bookRepository.save(BookEntityCase2.defaultBookOf("Pascal Book", "Some description", List.of(BookCategory.documentation, BookCategory.balalar), "Nik", "10.02.2001", "400 руб.", "300 руб."));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping("/book")
    public ResponseEntity<BookEntityCase2> getBookById(@RequestParam long id) {
        try {
            Optional<BookEntityCase2> found = bookRepository.findById(id);
            if (found.isEmpty()) {
                return ResponseEntity.internalServerError().build();
            }
            return ResponseEntity.ok(found.get());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/book")
    public ResponseEntity<BookEntityCase2> addBook(@RequestBody BookEntityCase2 bookEntity) {
        try {
            bookRepository.save(bookEntity);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/book")
    public ResponseEntity<BookEntityCase2> editBookById(@RequestParam long id,
                                                        @RequestBody BookEntityCase2 bookEntity) {
        try {
            Optional<BookEntityCase2> found = bookRepository.findById(id);
            found.ifPresent(entity -> bookRepository.save(entity.baseOnAddId(bookEntity, entity.getId())));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/book")
    public ResponseEntity<String> deleteBookById(@RequestParam long id) {
        try {

            bookRepository.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookEntityCase2>> getAllBook(@RequestParam(required = false) String author,
                                                            @RequestParam(required = false) BookCategory category,
                                                            @RequestParam(required = false) String year) {

        List<BookEntityCase2> allBooks = bookRepository.findAll();

        if (author != null) {
            return ResponseEntity.ok(allBooks.stream().filter(entity -> entity.getAuthor().equals(author)).toList());
        }

        if (category != null) {
            return ResponseEntity.ok(allBooks.stream().filter(entity -> entity.getCategory().contains(category)).toList());
        }

        if (year != null) {
            return ResponseEntity.ok(allBooks.stream().filter(entity -> entity.getDate().contains(year)).toList());
        }

        return ResponseEntity.ok(allBooks);
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyBook(@RequestParam String login,
                                          @RequestParam long bookId) {
        try {

            Optional<BookEntityCase2> found = bookRepository.findById(bookId);
            found.ifPresent(entity -> {
                entity.setLogin(login);
                entity.setAvailability(BookAvailabilityCase2.BUY);
                bookRepository.save(entity);
            });

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rent")
    public ResponseEntity<String> rentBook(@RequestParam String login,
                                           @RequestParam long bookId,
                                           @RequestParam String reservedUntil) {
        try {

            Optional<BookEntityCase2> found = bookRepository.findById(bookId);
            found.ifPresent(entity -> {
                entity.setLogin(login);
                entity.setAvailability(BookAvailabilityCase2.RENT);
                entity.setReservedUntil(reservedUntil);
                bookRepository.save(entity);
            });

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

}
