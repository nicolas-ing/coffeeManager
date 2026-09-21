package com.coffeemanager.coffeemanager.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String mensaje) {
        super(mensaje);
    }
}