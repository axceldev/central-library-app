package com.axceldev.central_library.repositories;

import com.axceldev.central_library.models.model.Stock;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StockRepository extends ReactiveCrudRepository<Stock, Integer> {
}
