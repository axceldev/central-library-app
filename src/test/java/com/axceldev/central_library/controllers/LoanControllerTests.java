package com.axceldev.central_library.controllers;

import com.axceldev.central_library.models.dto.rq.LoanRq;
import com.axceldev.central_library.models.dto.rs.CustomerRs;
import com.axceldev.central_library.models.dto.rs.LoanBookRs;
import com.axceldev.central_library.models.dto.rs.LoanRs;
import com.axceldev.central_library.models.model.Loan;
import com.axceldev.central_library.services.LoanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Date;

@WebFluxTest
public class LoanControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private LoanService loanService;

    @Test
    public void testCreateNewLoan() {
        LoanRq loanRq = new LoanRq();
        loanRq.setCustomerId(1);
        loanRq.setBookId(101);

        LoanRs loanRs = new LoanRs();
        loanRs.setLoanId(1);
        loanRs.setBorrowDate(new Date().toString());
        loanRs.setReturnDate(new Date().toString());

        Mockito.when(loanService.createNewLoanOfBook(Mockito.any(LoanRq.class)))
                .thenReturn(Mono.just(loanRs));

        webTestClient.post()
                .uri("/loan")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loanRq)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LoanRs.class)
                .value(returnedLoanRs -> {
                    // Assert the returned LoanRs matches the expected loanRs
                    Assertions.assertEquals(loanRs.getLoanId(), returnedLoanRs.getLoanId());
                    Assertions.assertEquals(loanRs.getBorrowDate(), returnedLoanRs.getBorrowDate());
                    Assertions.assertEquals(loanRs.getReturnDate(), returnedLoanRs.getReturnDate());
                });
    }

}