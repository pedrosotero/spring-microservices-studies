package com.dio.productcatalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DifferentIdsException extends Exception{

    public DifferentIdsException(Long requestId, Long productId){
        super(String.format("Diferent IDs: %d and %d", requestId, productId));
    }
}
