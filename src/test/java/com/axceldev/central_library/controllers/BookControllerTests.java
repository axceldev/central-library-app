package com.axceldev.central_library.controllers;

import com.axceldev.central_library.models.dto.rq.BookRq;
import com.axceldev.central_library.models.model.Book;
import com.axceldev.central_library.services.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@WebFluxTest
public class BookControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private BookService bookService;

    @Test
    public void testSaveBook() {
        BookRq bookRq = new BookRq();
        bookRq.setTitle("Test Book");
        bookRq.setAuthor("Test Author");
        bookRq.setYear(2023);
        bookRq.setPublisher("Test Publisher");
        bookRq.setCost(29.99);

        Book savedBook = new Book();
        savedBook.setBookId(1);
        savedBook.setTitle("Test Book");
        savedBook.setAuthor("Test Author");
        savedBook.setYearPublished(2023);
        savedBook.setPublisher("Test Publisher");
        savedBook.setCost(29.99);

        Mockito.when(bookService.saveNewBook(Mockito.any(BookRq.class)))
                .thenReturn(Mono.just(savedBook));

        webTestClient.post()
                .uri("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(bookRq)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .value(book -> {
                    // Assert the returned book matches the expected savedBook
                    Assertions.assertEquals(savedBook.getBookId(), book.getBookId());
                    Assertions.assertEquals(savedBook.getTitle(), book.getTitle());
                    Assertions.assertEquals(savedBook.getAuthor(), book.getAuthor());
                    Assertions.assertEquals(savedBook.getYearPublished(), book.getYearPublished());
                    Assertions.assertEquals(savedBook.getPublisher(), book.getPublisher());
                    Assertions.assertEquals(savedBook.getCost(), book.getCost());
                });
    }

    @Test
    public void testGetAllBook() {
        Book book1 = new Book(1, "Book 1", "Author 1", 2021, "Publisher 1", 19.99);
        Book book2 = new Book(2, "Book 2", "Author 2", 2022, "Publisher 2", 24.99);

        List<Book> bookList = Arrays.asList(book1, book2);

        Mockito.when(bookService.findAllBook())
                .thenReturn(Mono.just(bookList));

        webTestClient.get()
                .uri("/book")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Book.class)
                .hasSize(2)
                .contains(book1, book2);
    }

    @Test
    public void testGetBookById() {
        int bookId = 1;
        Book book = new Book(bookId, "Test Book", "Test Author", 2023, "Test Publisher", 29.99);

        Mockito.when(bookService.findBookById(bookId))
                .thenReturn(Mono.just(book));

        webTestClient.get()
                .uri("/book/{bookId}", bookId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .value(returnedBook -> {
                    // Assert the returned book matches the expected book
                    Assertions.assertEquals(book.getBookId(), returnedBook.getBookId());
                    Assertions.assertEquals(book.getTitle(), returnedBook.getTitle());
                    Assertions.assertEquals(book.getAuthor(), returnedBook.getAuthor());
                    Assertions.assertEquals(book.getYearPublished(), returnedBook.getYearPublished());
                    Assertions.assertEquals(book.getPublisher(), returnedBook.getPublisher());
                    Assertions.assertEquals(book.getCost(), returnedBook.getCost());
                });
    }
}