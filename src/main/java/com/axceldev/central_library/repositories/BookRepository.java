package com.axceldev.central_library.repositories;

import com.axceldev.central_library.models.model.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {}
