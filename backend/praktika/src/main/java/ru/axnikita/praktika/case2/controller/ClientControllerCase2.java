package ru.axnikita.praktika.case2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.axnikita.praktika.case2.entity.BookAvailabilityCase2;
import ru.axnikita.praktika.case2.entity.BookEntityCase2;
import ru.axnikita.praktika.case2.entity.BookStatusCase2;
import ru.axnikita.praktika.case2.repository.BookRepositoryCase2;

import java.util.List;

@RestController
@RequestMapping("/case2/client")
public class ClientControllerCase2 {

    private final BookRepositoryCase2 bookRepository;

    public ClientControllerCase2(BookRepositoryCase2 bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookEntityCase2>> getAllBook() {
        List<BookEntityCase2> books = bookRepository.findAll().stream()
                .filter(book -> book.getStatus().equals(BookStatusCase2.PUBLIC))
                .filter(book -> book.getAvailability().equals(BookAvailabilityCase2.AVAILABLE))
                .toList();
        return ResponseEntity.ok(books);
    }


}
