package com.example.baranclinic.crm.domain.repository;

import com.example.baranclinic.crm.domain.entity.Dog;
import com.example.baranclinic.crm.domain.entity.Owner;
import com.example.baranclinic.crm.domain.model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void shouldSaveAndFindOwner() {
        // Arrange
        Owner owner = createOwner();

        // Act
        Owner savedOwner = ownerRepository.save(owner);

        // Assert
        Optional<Owner> foundOwner = ownerRepository.findById(savedOwner.getId());

        assertThat(savedOwner.getId()).isNotNull();
        assertThat(foundOwner).isPresent();
        assertThat(foundOwner.get().getFirstName()).isEqualTo("John");
        assertThat(foundOwner.get().getAddress().getCity()).isEqualTo("Berlin");
    }

    @Test
    void addDogToOwner() {
        // Arrange
        Owner owner = createOwner();

        Dog dog = Dog.builder()
                .name("Cody")
                .breed("Poodle")
                .microchipId("CHIP456")
                .birthDate(LocalDate.of(2022, 1, 1))
                .weight(15.9)
                .owner(owner)
                .build();

        // Act
        owner.addDog(dog);

        // Arrange
        assertFalse(owner.getDogs().isEmpty());
        assertEquals(1, owner.getDogs().size());
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
}
