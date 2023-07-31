package com.axceldev.central_library.models.dto.rs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class DetailLoanRs {
    private Integer loanId;
    private String borrowDate;
    private String returnDate;
    private BookRs book;
}
