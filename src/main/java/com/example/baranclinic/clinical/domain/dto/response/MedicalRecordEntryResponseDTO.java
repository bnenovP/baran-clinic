package com.example.baranclinic.clinical.domain.dto.response;

import com.example.baranclinic.clinical.domain.entity.MedicalRecordEntry;
import com.example.baranclinic.clinical.domain.entity.Provider;
import com.example.baranclinic.crm.domain.entity.Dog;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class MedicalRecordEntryResponseDTO {

    UUID id;

    Dog dog;

    Provider provider;

    LocalDate date;

    MedicalRecordEntry.EntryType type;

    String notes;
}
