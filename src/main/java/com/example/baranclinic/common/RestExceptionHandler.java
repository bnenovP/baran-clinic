package com.example.baranclinic.common;

import com.example.baranclinic.common.dto.ErrorResponseDTO;
import com.example.baranclinic.crm.domain.common.exception.DogAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(HttpStatus.BAD_REQUEST, ex.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = DogAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleDogAlreadyExistsException(DogAlreadyExistsException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(HttpStatus.BAD_REQUEST, ex.getMessage());

        return ResponseEntity.badRequest().body(error);
    }
}
