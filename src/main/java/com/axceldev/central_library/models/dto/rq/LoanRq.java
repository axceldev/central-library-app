package com.axceldev.central_library.models.dto.rq;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoanRq {
    private Integer customerId;
    private Integer bookId;
}
