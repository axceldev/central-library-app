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
@Table(value = "books")
public class Book {
    @Id
    @Column(value = "book_id")
    public Integer bookId;
    public String title;
    public String author;
    @Column(value = "year_published")
    public int yearPublished;
    public String publisher;
    public double cost;
}
