package com.axceldev.central_library.repositories;

import com.axceldev.central_library.models.model.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
    Mono<Customer> findCustomerByDocumentNumber(String documentNumber);
}
