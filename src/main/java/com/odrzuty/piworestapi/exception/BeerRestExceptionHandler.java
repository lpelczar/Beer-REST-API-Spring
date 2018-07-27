package com.odrzuty.piworestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BeerRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BeerErrorResponse> handleException(ResourceNotFoundException e) {

        BeerErrorResponse beerErrorResponse = new BeerErrorResponse();
        beerErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        beerErrorResponse.setMessage(e.getMessage());
        beerErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(beerErrorResponse, HttpStatus.NOT_FOUND);
    }
}
