package com.axceldev.central_library.controllers;

import com.axceldev.central_library.models.model.Stock;
import com.axceldev.central_library.services.StockService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest
public class StockControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private StockService stockService;

    @Test
    public void testGetStockOfBook() {
        int bookId = 101;
        Stock stock = new Stock();
        stock.setStockId(bookId);
        stock.setQuantity(10);
        stock.setLocation("Library A");

        Mockito.when(stockService.findStockOfBook(bookId))
                .thenReturn(Mono.just(stock));

        webTestClient.get()
                .uri("/stock/{bookId}", bookId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Stock.class)
                .value(returnedStock -> {
                    // Assert the returned Stock matches the expected stock
                    Assertions.assertEquals(stock.getBookId(), returnedStock.getBookId());
                    Assertions.assertEquals(stock.getQuantity(), returnedStock.getQuantity());
                    Assertions.assertEquals(stock.getLocation(), returnedStock.getLocation());
                });
    }

    @Test
    public void testUpdateQuantityOfBook() {
        int bookId = 101;
        boolean updateResult = true;

        Mockito.when(stockService.updateQuantityOfBookByBookId(bookId))
                .thenReturn(Mono.just(updateResult));

        webTestClient.put()
                .uri("/stock/{bookId}", bookId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class)
                .isEqualTo(updateResult);
    }
}
