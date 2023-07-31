package com.axceldev.central_library.models.dto.rs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookRs {
    public String title;
    public String author;
    public int year;
    public String publisher;
    public double cost;
}
