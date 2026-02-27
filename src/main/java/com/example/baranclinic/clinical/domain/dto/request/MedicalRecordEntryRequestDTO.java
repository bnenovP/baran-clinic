package com.example.baranclinic.clinical.domain.dto.request;

import com.example.baranclinic.clinical.domain.entity.MedicalRecordEntry;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class MedicalRecordEntryRequestDTO {
    @NotNull UUID dogId;
    @NotNull UUID providerId;
    @NotNull MedicalRecordEntry.EntryType type;
    String notes;
    LocalDate date;
}
