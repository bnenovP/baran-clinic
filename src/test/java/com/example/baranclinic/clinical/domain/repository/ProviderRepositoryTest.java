package com.example.baranclinic.clinical.domain.repository;

import com.example.baranclinic.clinical.domain.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ProviderRepositoryTest {

    @Autowired
    private ProviderRepository providerRepository;

    @Test
    void shouldSaveAndFindProvider() {
        // Arrange
        Provider provider = Provider.builder()
                .firstName("Alice")
                .lastName("Wonder")
                .licenseNumber("VET-12345")
                .email("alice@clinic.com")
                .phoneNumber("+49000111222")
                .active(true)
                .build();

        // Act
        Provider savedProvider = providerRepository.save(provider);

        // Assert
        Optional<Provider> foundProvider = providerRepository.findByLicenseNumber("VET-12345");

        assertThat(savedProvider.getId()).isNotNull();
        assertThat(foundProvider).isPresent();
        assertThat(foundProvider.get().getFirstName()).isEqualTo("Alice");
    }
}
