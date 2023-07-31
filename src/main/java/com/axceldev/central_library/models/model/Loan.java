package com.axceldev.central_library.models.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(value = "loans")
public class Loan {

    @Id
    @Column(value = "loan_id")
    private Integer loanId;
    @Column(value = "borrow_date")
    private String borrowDate;
    @Column(value = "return_date")
    private String returnDate;
    @Column(value = "customer_id")
    private Integer customerId;
    @Column(value = "book_id")
    private Integer bookId;
}
