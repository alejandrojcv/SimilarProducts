package com.ams.SimilarProducts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class SimilarProductsException {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<String> allExceptionHandler(Exception e){
        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public final ResponseEntity<String> httpClientErrorException(HttpClientErrorException e){
        if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Error to integration application ", e.getStatusCode());
    }

}