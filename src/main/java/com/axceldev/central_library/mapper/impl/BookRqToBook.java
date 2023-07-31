package com.axceldev.central_library.mapper.impl;

import com.axceldev.central_library.mapper.IMapper;
import com.axceldev.central_library.models.dto.rq.BookRq;
import com.axceldev.central_library.models.model.Book;

public class BookRqToBook implements IMapper<BookRq, Book> {
    @Override
    public Book map(BookRq in) {
        return Book.builder()
                .title(in.title)
                .author(in.author)
                .yearPublished(in.year)
                .publisher(in.publisher)
                .cost(in.cost)
                .build();
    }
}
