package com.axceldev.central_library.models.dto.rs;

import com.axceldev.central_library.models.dto.rq.CustomerRq;
import lombok.*;


import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class LoanRs {
    private Integer loanId;
    private String borrowDate;
    private String returnDate;
    private CustomerRs customer;
    private BookRs book;
}
