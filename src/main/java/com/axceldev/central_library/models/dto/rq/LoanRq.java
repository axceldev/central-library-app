package com.axceldev.central_library.models.dto.rq;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoanRq {
    private Date borrowDate;
    private Date returnDate;
    private Integer customerId;
    private Integer bookId;
}
