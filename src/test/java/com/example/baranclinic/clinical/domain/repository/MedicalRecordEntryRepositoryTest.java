package com.example.baranclinic.clinical.domain.repository;

import com.example.baranclinic.clinical.domain.entity.MedicalRecordEntry;
import com.example.baranclinic.clinical.domain.entity.Provider;
import com.example.baranclinic.crm.domain.entity.Dog;
import com.example.baranclinic.crm.domain.entity.Owner;
import com.example.baranclinic.crm.domain.model.Address;
import com.example.baranclinic.crm.domain.repository.DogRepository;
import com.example.baranclinic.crm.domain.repository.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MedicalRecordEntryRepositoryTest {

    @Autowired
    private MedicalRecordEntryRepository medicalRecordEntryRepository;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void findMedicalRecordEntriesByDogId() {
        // Arrange
        Owner owner = setUpOwner();
        ownerRepository.save(owner);

        Dog dog = setDog(owner);
        dogRepository.save(dog);
        owner.addDog(dog);

        Provider provider = setUpProvider();
        providerRepository.save(provider);
        Provider existingProvider = providerRepository.findById(provider.getId()).get();

        MedicalRecordEntry medicalRecordEntry = MedicalRecordEntry.builder()
                .dog(dog)
                .provider(existingProvider)
                .date(LocalDate.now().minusDays(10))
                .type(MedicalRecordEntry.EntryType.VACCINATION)
                .notes("First vaccination")
                .build();
        medicalRecordEntryRepository.save(medicalRecordEntry);

        // Act
        List<MedicalRecordEntry> existingMedicalRecordEntries = medicalRecordEntryRepository.findByDogId(dog.getId());

        // Assert
        assertFalse(existingMedicalRecordEntries.isEmpty());
        assertEquals(1, existingMedicalRecordEntries.size());
        assertEquals(medicalRecordEntry.getId(), existingMedicalRecordEntries.get(0).getId());
    }

    private Dog setDog(Owner owner) {
        return Dog.builder()
                .name("Buddy")
                .breed("Golden Retriever")
                .microchipId("CHIP123")
                .birthDate(LocalDate.of(2020, 1, 1))
                .weight(30.5)
                .owner(owner)
                .build();
    }

    private Owner setUpOwner() {
        Address address = new Address("Berlin", "Street 1");

        return Owner.builder()
                .firstName("Jane")
                .lastName("Smith")
                .phoneNumber("+49987654321")
                .address(address)
                .build();
    }

    private Provider setUpProvider() {
        return Provider.builder()
                .firstName("Suzan")
                .lastName("Johnson")
                .email("sjohnson@gmail.com")
                .active(true)
                .licenseNumber("ASQ123ZXC")
                .phoneNumber("555-111-222")
                .build();
    }
}