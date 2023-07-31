package com.axceldev.central_library.services;

import com.axceldev.central_library.exceptions.BusinessException;
import com.axceldev.central_library.models.model.Stock;
import com.axceldev.central_library.repositories.StockRepository;
import com.axceldev.central_library.utils.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    public Mono<Stock> findStockOfBook(Integer bookId) {
        return stockRepository.findAllByBookId(bookId)
                .switchIfEmpty(Mono.error(new BusinessException(HttpStatus.NOT_FOUND, Boolean.FALSE, Message.BOOK_WITHOUT_STOCK)));
    }

    public Mono<Boolean> updateQuantityOfBookByBookId(Integer bookId) {
        return stockRepository.findAllByBookId(bookId).hasElement()
                .flatMap(exists -> {
                    if (exists) {
                        return stockRepository.findQuantityOfBook(bookId);
                    } else {
                        return Mono.error(new BusinessException(HttpStatus.NOT_FOUND, Boolean.FALSE, Message.BOOK_NOT_FOUND));
                    }
                })
                .flatMap(numOfCopies -> {
                    if (numOfCopies > 0) {
                        return stockRepository.updateQuantityByBookId(numOfCopies - 1, bookId).thenReturn(Boolean.TRUE);
                    } else {
                        return Mono.error(new BusinessException(HttpStatus.NOT_FOUND, Boolean.FALSE, Message.BOOK_WITHOUT_STOCK));
                    }
                });
    }
}
