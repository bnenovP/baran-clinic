package com.example.baranclinic.clinical.domain.dto.response;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class ProviderResponseDTO {

    UUID id;

    String firstName;

    String lastName;

    String licenseNumber;

    String email;

    String phoneNumber;

    List<MedicalRecordEntryResponseDTO> medicalRecordEntries;

    boolean active = true;
}
