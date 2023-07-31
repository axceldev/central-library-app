package com.axceldev.central_library.models.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(value = "stock")
public class Stock {
    @Id
    @Column(value = "stock_id")
    private Integer stockId;
    @Column(value = "book_id")
    private Integer bookId;
    private Integer quantity;
    private String location;
}
