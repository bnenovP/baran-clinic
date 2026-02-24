package com.example.baranclinic.crm.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class DogRequestDTO {

    @NotBlank
    String name;

    @NotBlank
    String microchipId;

    @NotBlank
    String breed;

    @NotNull
    LocalDate birthDate;

    @Positive
    double weight;

    @NotNull
    UUID ownerId;
}
