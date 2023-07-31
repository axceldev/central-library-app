package com.axceldev.central_library.utils;

public class Sql {
    public static final String UPDATE_QUANTITY_BOOK_ID = "UPDATE stock s SET s.quantity = ?1 WHERE s.book_id = ?2;";
    public static final String FIND_QUANTITY_BOOK_ID = "SELECT s.quantity FROM stock s WHERE s.book_id = ?1;";
}
