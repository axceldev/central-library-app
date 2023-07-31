package com.axceldev.central_library.controllers;

import com.axceldev.central_library.models.dto.rq.LoanRq;
import com.axceldev.central_library.models.dto.rs.LoanBookRs;
import com.axceldev.central_library.models.dto.rs.LoanRs;
import com.axceldev.central_library.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/library")
@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;
    @PostMapping("/loan")
    public Mono<LoanRs> createNewLoan(@RequestBody LoanRq loanRq){
        return loanService.createNewLoanOfBook(loanRq);
    }

    @GetMapping("/loan/{customerId}")
    public Mono<LoanBookRs> getAllLoanByCustomerId(@PathVariable("customerId") Integer customerId){
        return loanService.findAllLoanByCustomerId(customerId);
    }
}
