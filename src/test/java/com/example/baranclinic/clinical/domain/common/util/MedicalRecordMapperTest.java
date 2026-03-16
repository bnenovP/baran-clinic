package com.example.baranclinic.clinical.domain.common.util;

import com.example.baranclinic.clinical.domain.dto.request.MedicalRecordEntryRequestDTO;
import com.example.baranclinic.clinical.domain.dto.response.MedicalRecordEntryResponseDTO;
import com.example.baranclinic.clinical.domain.entity.MedicalRecordEntry;
import com.example.baranclinic.clinical.domain.entity.Provider;
import com.example.baranclinic.crm.domain.entity.Dog;
import com.example.baranclinic.crm.domain.entity.Owner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MedicalRecordMapperTest {

    private final UUID dogId =  UUID.randomUUID();

    private final UUID providerId = UUID.randomUUID();

    private final UUID ownerId = UUID.randomUUID();

    @InjectMocks
    private MedicalRecordMapper medicalRecordMapper;

    @Test
    void whenMapMedicalRecordEntryRequestDTOtoMedicalRecordEntry_returnMedicalRecordEntry() {
        // Arrange
        MedicalRecordEntryRequestDTO medicalRecordEntryRequestDTO = MedicalRecordEntryRequestDTO.builder()
                .dogId(dogId)
                .type(MedicalRecordEntry.EntryType.VACCINATION)
                .date(LocalDate.now().minusMonths(1))
                .notes("First vaccination")
                .providerId(providerId)
                .build();

        Owner owner =  Owner.builder()
                .id(ownerId)
                .build();

        Dog dog = Dog.builder()
                .id(dogId)
                .microchipId("CHIP123")
                .owner(owner)
                .build();

        Provider provider = Provider.builder()
                .id(providerId)
                .licenseNumber("LICENSE123")
                .build();

        // Act
        MedicalRecordEntry medicalRecordEntry = medicalRecordMapper
                .fromMedicalRecordEntryRequestDTOtoMedicalRecordEntry(medicalRecordEntryRequestDTO, dog, provider);

        // Assert
        assertEquals(medicalRecordEntry.getType(), medicalRecordEntryRequestDTO.getType());
        assertEquals(medicalRecordEntry.getDate(), medicalRecordEntryRequestDTO.getDate());
        assertEquals(medicalRecordEntry.getNotes(), medicalRecordEntryRequestDTO.getNotes());
        assertEquals(medicalRecordEntry.getDog().getId(), medicalRecordEntryRequestDTO.getDogId());
        assertEquals(medicalRecordEntry.getProvider().getId(), medicalRecordEntryRequestDTO.getProviderId());
    }

    @Test
    void whenMapMedicalRecordEntryToMedicalRecordResponseDTO_thenReturnMedicalRecordResponseDTO() {
        Owner owner = Owner.builder()
                .id(ownerId)
                .build();

        Dog dog = Dog.builder()
                .id(dogId)
                .owner(owner)
                .microchipId("CHIP123")
                .name("Rex")
                .build();

        Provider provider = Provider.builder()
                .id(providerId)
                .licenseNumber("LICENSE123")
                .firstName("Adam")
                .lastName("Smith")
                .email("adamsmith@gmail.com")
                .build();

        MedicalRecordEntry medicalRecordEntry = MedicalRecordEntry.builder()
                .type(MedicalRecordEntry.EntryType.VACCINATION)
                .date(LocalDate.now().minusMonths(2))
                .id(dogId)
                .notes("Second vaccination")
                .dog(dog)
                .provider(provider)
                .build();

        // Act
        MedicalRecordEntryResponseDTO medicalRecordEntryResponseDTO = medicalRecordMapper
                .fromMedicalRecordEntryToMedicalRecordResponseDTO(medicalRecordEntry);

        // Assert
        assertEquals(medicalRecordEntry.getType(), medicalRecordEntryResponseDTO.getType());
        assertEquals(medicalRecordEntry.getDate(), medicalRecordEntryResponseDTO.getDate());
        assertEquals(medicalRecordEntry.getNotes(), medicalRecordEntryResponseDTO.getNotes());
        assertEquals(medicalRecordEntry.getDog(), medicalRecordEntryResponseDTO.getDog());
        assertEquals(medicalRecordEntry.getProvider(), medicalRecordEntryResponseDTO.getProvider());
    }
}