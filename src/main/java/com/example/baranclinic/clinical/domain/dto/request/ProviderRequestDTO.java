package com.example.baranclinic.clinical.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProviderRequestDTO {
    @NotBlank String firstName;
    @NotBlank String lastName;
    @NotBlank String licenseNumber;
    @Email String email;
    String phoneNumber;
}
