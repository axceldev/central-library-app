package com.axceldev.central_library.services;

import com.axceldev.central_library.mapper.impl.BookRqToBook;
import com.axceldev.central_library.models.dto.rq.BookRq;
import com.axceldev.central_library.models.model.Book;
import com.axceldev.central_library.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    public final BookRepository bookRepository;

    public Mono<Book> saveNewBook(BookRq bookRq) {
        return bookRepository.save(new BookRqToBook().map(bookRq));
    }

    public Mono<List<Book>> getAllBook() {
        return bookRepository.findAll().collectList();
    }
}
