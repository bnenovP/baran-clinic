package com.example.baranclinic.clinical.domain.common.exception;

public class ProviderNotActiveException extends RuntimeException {
    public ProviderNotActiveException(String message) {
        super(message);
    }
}
