package com.dio.productcatalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(Long id){
        super("There is no product with id: " + id);
    }
}
