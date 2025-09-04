package com.prueba.prueba_tecnica.shared.error;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
