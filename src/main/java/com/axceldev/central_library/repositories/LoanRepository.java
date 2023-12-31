package com.axceldev.central_library.repositories;

import com.axceldev.central_library.models.model.Loan;
import com.axceldev.central_library.models.model.Stock;
import com.axceldev.central_library.utils.Sql;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

public interface LoanRepository extends ReactiveCrudRepository<Loan, Integer> {
    Flux<Loan> findAllByCustomerId(Integer customerId);
}
