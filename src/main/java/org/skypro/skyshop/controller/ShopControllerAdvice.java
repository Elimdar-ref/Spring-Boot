package org.skypro.skyshop.controller;

import org.skypro.skyshop.error.NoSuchProductException;
import org.skypro.skyshop.error.ShopError;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
public class ShopControllerAdvice{
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> noSuchProductException(NoSuchProductException e){
        ShopError shopError = new ShopError("PRODUCT_NOT_FOUND", "Товар не найден");
        return new ResponseEntity<ShopError>(shopError, HttpStatusCode.valueOf(404));
    }
}