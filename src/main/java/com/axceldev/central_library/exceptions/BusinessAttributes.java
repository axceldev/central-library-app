package com.axceldev.central_library.exceptions;

import org.springframework.boot.web.error.ErrorAttributeOptions;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class BusinessAttributes extends DefaultErrorAttributes {

    private String patternDate = "dd-MM-yyyy HH:mm:ss";

    private Date date = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternDate);

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = new HashMap<>();

        Throwable throwable = super.getError(webRequest);

        if(throwable instanceof BusinessException businessException){
            errorAttributes.put("date", simpleDateFormat.format(date));
            errorAttributes.put("code", businessException.getCode());
            errorAttributes.put("status", businessException.getStatus());
            errorAttributes.put("message", businessException.getMessage());
        }

        return errorAttributes;
    }
}
