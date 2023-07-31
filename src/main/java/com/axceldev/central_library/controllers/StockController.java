package com.axceldev.central_library.controllers;

import com.axceldev.central_library.models.model.Stock;
import com.axceldev.central_library.services.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicBoolean;

@RequestMapping("/library")
@RestController
@RequiredArgsConstructor
public class StockController {
    public final StockService stockService;

    @GetMapping("/stock/{bookId}")
    public Mono<Stock> getStockOfBook(@PathVariable("bookId") Integer bookId){
        return stockService.findStockOfBook(bookId);
    }

    @PutMapping("/stock/{bookId}")
    public Mono<Boolean> updateQuantityOfBook(@PathVariable("bookId") Integer bookId){
        return stockService.updateQuantityOfBookByBookId(bookId);
    }

}
