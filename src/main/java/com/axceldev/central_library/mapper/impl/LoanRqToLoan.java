package com.axceldev.central_library.mapper.impl;

import com.axceldev.central_library.mapper.IMapper;
import com.axceldev.central_library.models.dto.rq.LoanRq;
import com.axceldev.central_library.models.model.Loan;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class LoanRqToLoan implements IMapper<LoanRq, Loan> {
    @Override
    public Loan map(LoanRq in) {
        LocalDate borrowDate = LocalDate.now();
        LocalDate returnDate = borrowDate.plusDays(7);

        return Loan.builder()
                .borrowDate(java.sql.Date.valueOf(borrowDate).toString())
                .returnDate(java.sql.Date.valueOf(returnDate).toString())
                .bookId(in.getBookId())
                .customerId(in.getCustomerId())
                .build();
    }
}
