package com.example.baranclinic.crm.domain.rest;

import com.example.baranclinic.crm.domain.dto.request.DogRequestDTO;
import com.example.baranclinic.crm.domain.dto.response.DogResponseDTO;
import com.example.baranclinic.crm.domain.service.DogService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "api/v1/dogs")
@AllArgsConstructor
public class DogController {

    private final DogService dogService;

    @PostMapping
    public ResponseEntity<DogResponseDTO> createDog(@Valid @RequestBody DogRequestDTO requestDTO) {
        DogResponseDTO dogResponseDTO = dogService.registerDog(requestDTO, requestDTO.getOwnerId());

        return new ResponseEntity<>(dogResponseDTO, HttpStatus.CREATED);
    }
}
