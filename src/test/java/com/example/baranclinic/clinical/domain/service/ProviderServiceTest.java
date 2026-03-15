package com.example.baranclinic.clinical.domain.service;

import com.example.baranclinic.clinical.domain.common.exception.LicenseDuplicationException;
import com.example.baranclinic.clinical.domain.common.util.ProviderMapper;
import com.example.baranclinic.clinical.domain.dto.request.ProviderRequestDTO;
import com.example.baranclinic.clinical.domain.entity.Provider;
import com.example.baranclinic.clinical.domain.repository.ProviderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProviderServiceTest {

    private final UUID providerId = UUID.randomUUID();

    @InjectMocks
    private ProviderService providerService;

    @Mock
    private ProviderRepository providerRepository;

    @Mock
    private ProviderMapper providerMapper;

    @Test
    void whenOnboardProvider_thenReturnProvider() {
        // Arrange
        ProviderRequestDTO providerRequestDTO = createProviderRequestDTO();
        Provider provider = createProvider();

        when(providerMapper.mapProviderRequestDTOtoProvider(providerRequestDTO))
                .thenReturn(provider);
        when(providerRepository.findByLicenseNumber(providerRequestDTO.getLicenseNumber()))
                .thenReturn(Optional.empty());
        when(providerRepository.save(provider)).thenReturn(provider);

        // Act
        Provider expectedProvider = providerService.onboardProvider(providerRequestDTO);

        // Assert
        assertEquals(expectedProvider.getId(), provider.getId());
        assertEquals(expectedProvider.getFirstName(), provider.getFirstName());
        assertEquals(expectedProvider.getLastName(), provider.getLastName());
        assertEquals(expectedProvider.getEmail(), provider.getEmail());
        assertEquals(expectedProvider.getPhoneNumber(), provider.getPhoneNumber());
    }

    @Test
    void whenOnboardProviderWithDuplicatedLicenseNumber_thenThrowsLicenseDuplicateException() {
        // Arrange
        ProviderRequestDTO providerRequestDTO = createProviderRequestDTO();
        Provider provider = createProvider();

        when(providerRepository.findByLicenseNumber(providerRequestDTO.getLicenseNumber()))
                .thenReturn(Optional.of(provider));

        // Act & Assert
        assertThrows(LicenseDuplicationException.class, () -> providerService.onboardProvider(providerRequestDTO));
    }

    private ProviderRequestDTO createProviderRequestDTO() {
        return ProviderRequestDTO.builder()
                .firstName("Adam")
                .lastName("Smith")
                .email("adamsmith@gmail.com")
                .phoneNumber("12345678")
                .licenseNumber("ASD1234")
                .build();
    }

    private Provider createProvider() {
        return Provider.builder()
                .id(providerId)
                .firstName("Adam")
                .lastName("Smith")
                .email("adamsmith@gmail.com")
                .phoneNumber("12345678")
                .licenseNumber("ASD1234")
                .build();
    }
}
