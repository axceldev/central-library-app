package com.axceldev.central_library.services;

import com.axceldev.central_library.exceptions.BusinessException;
import com.axceldev.central_library.mapper.impl.BookRqToBook;
import com.axceldev.central_library.models.dto.rq.BookRq;
import com.axceldev.central_library.models.model.Book;
import com.axceldev.central_library.repositories.BookRepository;
import com.axceldev.central_library.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    public Mono<Book> getBookById(Integer bookId) {
        return bookRepository
                .findById(bookId)
                .switchIfEmpty(Mono.error(new BusinessException(HttpStatus.NOT_FOUND, Boolean.FALSE, Message.BOOK_NOT_FOUND)));
    }
}
