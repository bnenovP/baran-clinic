package com.example.baranclinic.clinical.domain.common.util;

import com.example.baranclinic.clinical.domain.dto.request.ProviderRequestDTO;
import com.example.baranclinic.clinical.domain.dto.response.ProviderResponseDTO;
import com.example.baranclinic.clinical.domain.entity.Provider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProviderMapper {

    private final MedicalRecordMapper medicalRecordMapper;

    public Provider mapProviderRequestDTOtoProvider(ProviderRequestDTO request) {
        return Provider.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .licenseNumber(request.getLicenseNumber())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .active(true)
                .build();
    }

    public ProviderResponseDTO mapProviderToProviderResponseDTO(Provider provider) {
        return ProviderResponseDTO.builder()
                .id(provider.getId())
                .firstName(provider.getFirstName())
                .lastName(provider.getLastName())
                .licenseNumber(provider.getLicenseNumber())
                .email(provider.getEmail())
                .phoneNumber(provider.getPhoneNumber())
                .medicalRecordEntries(
                        provider.getMedicalRecordEntries()
                        .stream()
                        .map(medicalRecordMapper::fromMedicalRecordEntryToMedicalRecordResponseDTO).toList()
                )
                .build();
    }
}
