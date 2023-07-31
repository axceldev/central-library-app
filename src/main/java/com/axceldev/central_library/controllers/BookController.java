package com.axceldev.central_library.controllers;

import com.axceldev.central_library.models.dto.rq.BookRq;
import com.axceldev.central_library.models.model.Book;
import com.axceldev.central_library.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RequestMapping("/library")
@RestController
@RequiredArgsConstructor
public class BookController {

    public final BookService bookService;

    @PostMapping("/book")
    public Mono<Book> saveBook(@RequestBody BookRq bookRq){
        return this.bookService.saveNewBook(bookRq);
    }

    @GetMapping("/book")
    public Mono<List<Book>> getAllBook(){
        return bookService.findAllBook();
    }

    @GetMapping("/book/{bookId}")
    public Mono<Book> getBookById(@PathVariable("bookId") Integer bookId){
        return bookService.findBookById(bookId);
    }



}
