package com.example.baranclinic.clinical.domain.service;

import com.example.baranclinic.clinical.domain.common.util.ProviderMapper;
import com.example.baranclinic.clinical.domain.dto.request.ProviderRequestDTO;
import com.example.baranclinic.clinical.domain.entity.Provider;
import com.example.baranclinic.clinical.domain.repository.ProviderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProviderService {

    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;

    @Transactional
    public Provider onboardProvider(ProviderRequestDTO request) {
        if (providerRepository.findByLicenseNumber(request.getLicenseNumber()).isPresent()) {
            throw new IllegalStateException("Provider with license " + request.getLicenseNumber() + " already exists");
        }
        Provider provider = providerMapper.toEntity(request);
        return providerRepository.save(provider);
    }

    @Transactional
    public void deactivateProvider(UUID id) {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found"));
        provider.deactivate();
        providerRepository.save(provider);
    }
}
