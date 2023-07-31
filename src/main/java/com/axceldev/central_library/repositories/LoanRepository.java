package com.axceldev.central_library.repositories;

import com.axceldev.central_library.models.model.Loan;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LoanRepository extends ReactiveCrudRepository<Loan, Integer> {
}
