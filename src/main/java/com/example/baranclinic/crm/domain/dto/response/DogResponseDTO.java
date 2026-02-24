package com.example.baranclinic.crm.domain.dto.response;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class DogResponseDTO {

    UUID id;

    String name;

    String microchipId;

    String breed;

    LocalDate birthDate;

    double weight;

    UUID ownerId;
}
