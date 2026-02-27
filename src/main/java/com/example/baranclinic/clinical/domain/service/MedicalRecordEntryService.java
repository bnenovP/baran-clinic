package com.example.baranclinic.clinical.domain.service;

import com.example.baranclinic.clinical.domain.common.util.MedicalRecordMapper;
import com.example.baranclinic.clinical.domain.dto.request.MedicalRecordEntryRequestDTO;
import com.example.baranclinic.clinical.domain.entity.MedicalRecordEntry;
import com.example.baranclinic.clinical.domain.entity.Provider;
import com.example.baranclinic.clinical.domain.repository.MedicalRecordEntryRepository;
import com.example.baranclinic.clinical.domain.repository.ProviderRepository;
import com.example.baranclinic.crm.domain.entity.Dog;
import com.example.baranclinic.crm.domain.repository.DogRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MedicalRecordEntryService {

    private final MedicalRecordEntryRepository medicalRecordEntryRepository;
    private final DogRepository dogRepository;
    private final ProviderRepository providerRepository;
    private final MedicalRecordMapper medicalRecordMapper;

    @Transactional
    public MedicalRecordEntry createEntry(MedicalRecordEntryRequestDTO request) {
        Dog dog = dogRepository.findById(request.getDogId())
                .orElseThrow(() -> new EntityNotFoundException("Dog not found"));

        Provider provider = providerRepository.findById(request.getProviderId())
                .orElseThrow(() -> new EntityNotFoundException("Provider not found"));

        if (!provider.isActive()) {
            throw new IllegalStateException("Cannot create entry for inactive provider");
        }

        MedicalRecordEntry entry = medicalRecordMapper.toEntity(request, dog, provider);
        return medicalRecordEntryRepository.save(entry);
    }
}
