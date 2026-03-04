package com.example.baranclinic.clinical.domain.common.util;

import com.example.baranclinic.clinical.domain.dto.request.MedicalRecordEntryRequestDTO;
import com.example.baranclinic.clinical.domain.dto.response.MedicalRecordEntryResponseDTO;
import com.example.baranclinic.clinical.domain.entity.MedicalRecordEntry;
import com.example.baranclinic.clinical.domain.entity.Provider;
import com.example.baranclinic.crm.domain.entity.Dog;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MedicalRecordMapper {

    public MedicalRecordEntry fromMedicalRecordEntryRequestDTOtoMedicalRecordEntry(
            MedicalRecordEntryRequestDTO request,
            Dog dog,
            Provider provider
    ) {
        return MedicalRecordEntry.builder()
                .dog(dog)
                .provider(provider)
                .type(request.getType())
                .notes(request.getNotes())
                .date(request.getDate() != null ? request.getDate() : LocalDate.now())
                .build();
    }

    public MedicalRecordEntryResponseDTO fromMedicalRecordEntryToMedicalRecordResponseDTO(
            MedicalRecordEntry entry
    ) {
        return MedicalRecordEntryResponseDTO.builder()
                .id(entry.getId())
                .dog(entry.getDog())
                .provider(entry.getProvider())
                .type(entry.getType())
                .date(entry.getDate())
                .notes(entry.getNotes())
                .build();
    }
}
