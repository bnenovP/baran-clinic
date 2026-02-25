package com.example.baranclinic.crm.domain.repository;

import com.example.baranclinic.crm.domain.entity.Dog;
import com.example.baranclinic.crm.domain.entity.Owner;
import com.example.baranclinic.crm.domain.model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class DogRepositoryTest {

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void shouldSaveAndFindDog() {
        // Arrange
        Owner owner = setUpOwner();
        ownerRepository.save(owner);

        Dog dog = Dog.builder()
                .name("Buddy")
                .breed("Golden Retriever")
                .microchipId("CHIP123")
                .birthDate(LocalDate.of(2020, 1, 1))
                .weight(30.5)
                .owner(owner)
                .build();

        // Act
        Dog savedDog = dogRepository.save(dog);
        owner.addDog(dog);

        // Assert
        Optional<Dog> foundDog = dogRepository.findByMicrochipId("CHIP123");

        assertThat(savedDog.getId()).isNotNull();
        assertThat(foundDog).isPresent();
        assertThat(foundDog.get().getName()).isEqualTo("Buddy");
        assertThat(foundDog.get().getOwner().getLastName()).isEqualTo("Smith");
    }

    private Owner setUpOwner() {
        Address address = new Address("Berlin", "Street 1");

        return Owner.builder()
                .firstName("Jane")
                .lastName("Smith")
                .phoneNumber("+49987654321")
                .address(address)
                .dogs(new ArrayList<>())
                .build();
    }
}
