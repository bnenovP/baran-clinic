package com.example.baranclinic.crm.domain.repository;

import com.example.baranclinic.crm.domain.entity.Owner;
import com.example.baranclinic.crm.domain.model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void shouldSaveAndFindOwner() {
        // Arrange
        Address address = new Address("Berlin", "Street 1");

        Owner owner = Owner.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("+49123456789")
                .email("john.doe@example.com")
                .address(address)
                .build();

        // Act
        Owner savedOwner = ownerRepository.save(owner);

        // Assert
        Optional<Owner> foundOwner = ownerRepository.findById(savedOwner.getId());

        assertThat(savedOwner.getId()).isNotNull();
        assertThat(foundOwner).isPresent();
        assertThat(foundOwner.get().getFirstName()).isEqualTo("John");
        assertThat(foundOwner.get().getAddress().getCity()).isEqualTo("Berlin");
    }
}
