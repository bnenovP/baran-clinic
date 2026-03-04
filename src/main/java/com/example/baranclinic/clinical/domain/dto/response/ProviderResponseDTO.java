package com.example.baranclinic.clinical.domain.dto.response;

import com.example.baranclinic.clinical.domain.entity.MedicalRecordEntry;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
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
