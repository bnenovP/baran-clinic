package com.example.baranclinic.clinical.domain.common.util;

import com.example.baranclinic.clinical.domain.dto.request.ProviderRequestDTO;
import com.example.baranclinic.clinical.domain.entity.Provider;
import org.springframework.stereotype.Component;

@Component
public class ProviderMapper {

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
}
