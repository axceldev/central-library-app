package com.axceldev.central_library.models.dto.rs;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class LoanBookRs {
    private CustomerRs customer;
    private List<DetailLoanRs> books;
}
