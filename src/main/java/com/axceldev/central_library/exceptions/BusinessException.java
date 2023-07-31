package com.axceldev.central_library.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessException extends Exception{
    private HttpStatus code;
    private Boolean status;

    public BusinessException(HttpStatus code, Boolean status, String message) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public HttpStatus getCode() {
        return code;
    }

    public Boolean getStatus() {
        return status;
    }
}
