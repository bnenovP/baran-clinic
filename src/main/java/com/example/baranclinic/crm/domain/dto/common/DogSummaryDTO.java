package com.example.baranclinic.crm.domain.dto.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class DogSummaryDTO {

    @NotNull
    UUID id;

    @NotBlank
    String name;

    @NotBlank
    String breed;
}
