package com.example.baranclinic.crm.domain.dto.request;

import com.example.baranclinic.crm.domain.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OwnerRequestDTO {

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    String phoneNumber;

    @Email
    String email;

    @NotNull
    Address address;
}
