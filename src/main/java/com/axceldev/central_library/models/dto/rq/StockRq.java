package com.axceldev.central_library.models.dto.rq;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class StockRq {
    private Integer bookId;
    private Integer quantity;
    private String location;
}
