package com.example.baranclinic.clinical.domain.common.util;

import com.example.baranclinic.clinical.domain.dto.request.ProviderRequestDTO;
import com.example.baranclinic.clinical.domain.dto.response.ProviderResponseDTO;
import com.example.baranclinic.clinical.domain.entity.Provider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProviderMapperTest {

    @InjectMocks
    private ProviderMapper providerMapper;

    @Mock
    private MedicalRecordMapper medicalRecordMapper;

    @Test
    void whenMapProviderRequestDTOtoProvider_thenReturnProvider() {
        // Arrange
        ProviderRequestDTO providerRequestDTO = ProviderRequestDTO.builder()
                .email("adamsmith@gmail.com")
                .firstName("Adam")
                .lastName("Smith")
                .licenseNumber("LICENSE123")
                .phoneNumber("12345678")
                .build();

        // Act
        Provider provider = providerMapper.mapProviderRequestDTOtoProvider(providerRequestDTO);

        // Assert
        assertEquals(provider.getFirstName(), providerRequestDTO.getFirstName());
        assertEquals(provider.getLastName(), providerRequestDTO.getLastName());
        assertEquals(provider.getLicenseNumber(), providerRequestDTO.getLicenseNumber());
        assertEquals(provider.getPhoneNumber(), providerRequestDTO.getPhoneNumber());
        assertEquals(provider.getEmail(), providerRequestDTO.getEmail());
    }

    @Test
    void whenMapProviderToProviderResponseDTO_thenReturnProviderResponseDTO() {
        // Arrange
        UUID providerId = UUID.randomUUID();
        Provider provider = Provider.builder()
                .id(providerId)
                .email("adamsmith@gmail.com")
                .firstName("Adam")
                .lastName("Smith")
                .licenseNumber("LICENSE123")
                .phoneNumber("12345678")
                .medicalRecordEntries(Collections.emptyList())
                .build();

        // Act
        ProviderResponseDTO providerResponseDTO = providerMapper.mapProviderToProviderResponseDTO(provider);

        // Assert
        assertEquals(providerResponseDTO.getId(), provider.getId());
        assertEquals(providerResponseDTO.getFirstName(), provider.getFirstName());
        assertEquals(providerResponseDTO.getLastName(), provider.getLastName());
        assertEquals(providerResponseDTO.getLicenseNumber(), provider.getLicenseNumber());
        assertEquals(providerResponseDTO.getPhoneNumber(), provider.getPhoneNumber());
        assertEquals(providerResponseDTO.getEmail(), provider.getEmail());
    }
}