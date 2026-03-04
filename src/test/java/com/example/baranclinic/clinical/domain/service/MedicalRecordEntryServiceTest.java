package com.example.baranclinic.clinical.domain.service;

import com.example.baranclinic.clinical.domain.common.util.MedicalRecordMapper;
import com.example.baranclinic.clinical.domain.dto.request.MedicalRecordEntryRequestDTO;
import com.example.baranclinic.clinical.domain.entity.MedicalRecordEntry;
import com.example.baranclinic.clinical.domain.entity.Provider;
import com.example.baranclinic.clinical.domain.repository.MedicalRecordEntryRepository;
import com.example.baranclinic.clinical.domain.repository.ProviderRepository;
import com.example.baranclinic.crm.domain.entity.Dog;
import com.example.baranclinic.crm.domain.entity.Owner;
import com.example.baranclinic.crm.domain.model.Address;
import com.example.baranclinic.crm.domain.repository.DogRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.ProviderNotFoundException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MedicalRecordEntryServiceTest {

    @Mock
    private MedicalRecordEntryRepository medicalRecordEntryRepository;

    @Mock
    private DogRepository dogRepository;

    @Mock
    private ProviderRepository providerRepository;

    @Mock
    private MedicalRecordMapper medicalRecordMapper;

    @InjectMocks
    private MedicalRecordEntryService medicalRecordEntryService;

    @Test
    void whenCreateMedicalRecordEntryWithNonExistingDog_throwEntityNotFoundException() {
        // Arrange
        UUID dogId = UUID.randomUUID();
        UUID providerId = UUID.randomUUID();

        MedicalRecordEntryRequestDTO medicalRecordEntryRequestDTO = MedicalRecordEntryRequestDTO
                .builder()
                .dogId(dogId)
                .providerId(providerId)
                .type(MedicalRecordEntry.EntryType.VACCINATION)
                .date(LocalDate.now())
                .build();

        when(dogRepository.findById(dogId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class,
                () -> medicalRecordEntryService.createEntry(medicalRecordEntryRequestDTO));
    }

    @Test
    void whenCreateMedicalRecordEntryWithNonExistingProvider_throwEntityNotFoundException() {
        // Arrange
        Owner owner = createOwner();
        UUID dogId = UUID.randomUUID();
        UUID providerId = UUID.randomUUID();

        Dog dog = createDog(dogId, owner);

        MedicalRecordEntryRequestDTO medicalRecordEntryRequestDTO = MedicalRecordEntryRequestDTO
                .builder()
                .dogId(dogId)
                .providerId(providerId)
                .type(MedicalRecordEntry.EntryType.VACCINATION)
                .date(LocalDate.now())
                .build();

        when(dogRepository.findById(dogId)).thenReturn(Optional.of(dog));
        when(providerRepository.findById(medicalRecordEntryRequestDTO.getProviderId()))
                .thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class,
                () -> medicalRecordEntryService.createEntry(medicalRecordEntryRequestDTO));
    }

    @Test
    void whenCreateMedicalRecordEntryWithInactiveProvider_throwEntityNotFoundException() {
        // Arrange
        Owner owner = createOwner();
        UUID dogId = UUID.randomUUID();
        UUID providerId = UUID.randomUUID();

        Provider provider = createProvider(providerId);

        Dog dog = createDog(dogId, owner);

        MedicalRecordEntryRequestDTO medicalRecordEntryRequestDTO = MedicalRecordEntryRequestDTO
                .builder()
                .dogId(dogId)
                .providerId(provider.getId())
                .type(MedicalRecordEntry.EntryType.VACCINATION)
                .date(LocalDate.now())
                .build();
        provider.deactivate();

        when(dogRepository.findById(dogId)).thenReturn(Optional.of(dog));
        when(providerRepository.findById(medicalRecordEntryRequestDTO.getProviderId()))
                .thenReturn(Optional.of(provider));

        // Act and Assert
        assertThrows(ProviderNotFoundException.class,
                () -> medicalRecordEntryService.createEntry(medicalRecordEntryRequestDTO));
    }

    private static Dog createDog(UUID dogId, Owner owner) {
        return Dog.builder()
                .id(dogId)
                .owner(owner)
                .microchipId("CHIP123")
                .birthDate(LocalDate.now().minusYears(1))
                .name("Charley")
                .build();
    }

    private static Owner createOwner() {
        Address address = new Address("Berlin", "Street 1");

        return Owner.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("+49123456789")
                .email("john.doe@example.com")
                .address(address)
                .build();
    }

    private static Provider createProvider(UUID providerId) {
        return Provider.builder()
                .id(providerId)
                .licenseNumber("TEST123")
                .firstName("John")
                .lastName("Doe")
                .email("jdoe@gmail.com")
                .phoneNumber("555-111-123")
                .build();
    }
}