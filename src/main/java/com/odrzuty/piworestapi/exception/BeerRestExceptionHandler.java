package com.odrzuty.piworestapi.exception;

import com.odrzuty.piworestapi.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BeerRestExceptionHandler {

    private final LoggerService loggerService;

    @Autowired
    public BeerRestExceptionHandler(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @ExceptionHandler
    public ResponseEntity<BeerErrorResponse> handleException(ResourceNotFoundException e) {

        BeerErrorResponse beerErrorResponse = new BeerErrorResponse();
        beerErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        beerErrorResponse.setMessage(e.getMessage());
        beerErrorResponse.setTimeStamp(System.currentTimeMillis());

        loggerService.logError("(APPLICATION) Resource not found exception: " + beerErrorResponse.getMessage());

        return new ResponseEntity<>(beerErrorResponse, HttpStatus.NOT_FOUND);
    }
}
