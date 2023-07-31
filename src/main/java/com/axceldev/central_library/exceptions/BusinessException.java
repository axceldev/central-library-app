package com.axceldev.central_library.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class BusinessException extends RuntimeException{
    LocalDateTime currentDate;

    public BusinessException(String message) {
        super(message);
        this.currentDate = LocalDateTime.now();
    }
}
