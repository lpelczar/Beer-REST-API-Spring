package com.odrzuty.piworestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceRelatedException extends RuntimeException {

//    private String resourceName;
//    private String fieldName;
//    private Object fieldValue;

    public ResourceRelatedException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s  %s : '%s' cannot be removed because it related to another resource", resourceName, fieldName, fieldValue));
//        this.resourceName = resourceName;
//        this.fieldName = fieldName;
//        this.fieldValue = fieldValue;
    }
}
