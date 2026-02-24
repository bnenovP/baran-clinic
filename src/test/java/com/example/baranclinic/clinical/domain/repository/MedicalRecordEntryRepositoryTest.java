package com.example.baranclinic.clinical.domain.repository;

import com.example.baranclinic.clinical.domain.entity.MedicalRecordEntry;
import com.example.baranclinic.clinical.domain.entity.Provider;
import com.example.baranclinic.clinical.domain.valueobject.EntryType;
import com.example.baranclinic.crm.domain.entity.Dog;
import com.example.baranclinic.crm.domain.entity.Owner;
import com.example.baranclinic.crm.domain.repository.DogRepository;
import com.example.baranclinic.crm.domain.repository.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MedicalRecordEntryRepositoryTest {

    @Autowired
    private MedicalRecordEntryRepository medicalRecordEntryRepository;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Test
    void shouldSaveAndFindMedicalRecord() {
        // Arrange
        Owner owner = Owner.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123456")
                .build();
        ownerRepository.save(owner);

        Dog dog = Dog.builder()
                .name("Buddy")
                .owner(owner)
                .weight(25.0)
                .build();
        dogRepository.save(dog);

        Provider provider = Provider.builder()
                .firstName("Vet")
                .lastName("One")
                .licenseNumber("L-1")
                .email("vet@clinic.com")
                .phoneNumber("999")
                .active(true)
                .build();
        providerRepository.save(provider);

        MedicalRecordEntry entry = MedicalRecordEntry.builder()
                .dog(dog)
                .provider(provider)
                .type(EntryType.VACCINATION)
                .date(LocalDate.now())
                .notes("All good")
                .build();

        // Act
        MedicalRecordEntry savedEntry = medicalRecordEntryRepository.save(entry);

        // Assert
        List<MedicalRecordEntry> records = medicalRecordEntryRepository.findByDogId(dog.getId());

        assertThat(savedEntry.getId()).isNotNull();
        assertThat(records).hasSize(1);
        assertThat(records.get(0).getType()).isEqualTo(EntryType.VACCINATION);
    }
}
