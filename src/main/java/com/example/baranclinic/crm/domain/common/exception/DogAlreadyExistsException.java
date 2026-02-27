package com.example.baranclinic.crm.domain.common.exception;

public class DogAlreadyExistsException extends RuntimeException {

    public DogAlreadyExistsException(String message) {
        super(message);
    }
}
