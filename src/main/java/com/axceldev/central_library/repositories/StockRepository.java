package com.axceldev.central_library.repositories;

import com.axceldev.central_library.models.model.Stock;
import com.axceldev.central_library.utils.Sql;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface StockRepository extends ReactiveCrudRepository<Stock, Integer> {
    Mono<Stock> findAllByBookId(Integer bookId);
    @Query(value = Sql.FIND_QUANTITY_BOOK_ID)
    Mono<Integer> findQuantityOfBook(Integer bookId);
    @Query(value = Sql.UPDATE_QUANTITY_BOOK_ID)
    Mono<Void> updateQuantityByBookId(Integer quantity, Integer bookId);

}
