package com.example.baranclinic.crm.domain.service;

import com.example.baranclinic.crm.domain.dto.request.DogRequestDTO;
import com.example.baranclinic.crm.domain.dto.request.OwnerRequestDTO;
import com.example.baranclinic.crm.domain.dto.response.DogResponseDTO;
import com.example.baranclinic.crm.domain.dto.response.OwnerResponseDTO;
import com.example.baranclinic.crm.domain.entity.Dog;
import com.example.baranclinic.crm.domain.entity.Owner;
import com.example.baranclinic.crm.domain.repository.OwnerRepository;
import com.example.baranclinic.crm.domain.common.util.DogMapper;
import com.example.baranclinic.crm.domain.common.util.OwnerMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class OwnerService {

    private final OwnerMapper ownerMapper;

    private final DogMapper dogMapper;

    private final OwnerRepository ownerRepository;

    private final DogService dogService;

    @Transactional
    public OwnerResponseDTO registerOwner(OwnerRequestDTO ownerRequestDTO) {
        Owner owner = ownerMapper.fromOwnerRequestDTOtoOwner(ownerRequestDTO);
        ownerRepository.save(owner);

        return ownerMapper.fromOwnerToResponseOwnerDTO(owner);
    }

    @Transactional
    public OwnerResponseDTO getOwnerById(UUID id) {
        Owner owner = findOwnerOrThrow(id);

        return ownerMapper.fromOwnerToResponseOwnerDTO(owner);
    }

    @Transactional
    public DogResponseDTO addDog(UUID ownerId, DogRequestDTO dogRequestDTO) {
        Owner owner = findOwnerOrThrow(ownerId);
        Dog newDog = dogMapper.mapDogSummaryDTOtoDog(dogRequestDTO, owner);
        owner.addDog(newDog);

        return dogService.registerDog(dogRequestDTO, ownerId);
    }

    /**
     * Shared logic to find an owner or fail with a consistent exception.
     */
    private Owner findOwnerOrThrow(UUID id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with ID: " + id));
    }
}
