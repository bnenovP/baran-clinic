package com.example.baranclinic.crm.domain.service;

import com.example.baranclinic.crm.domain.dto.request.OwnerRequestDTO;
import com.example.baranclinic.crm.domain.entity.Owner;
import com.example.baranclinic.crm.domain.repository.OwnerRepository;
import com.example.baranclinic.crm.domain.util.OwnerMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OwnerService {

    private final OwnerMapper ownerMapper;

    private final OwnerRepository ownerRepository;

    public Owner registerOwner(@Valid OwnerRequestDTO ownerRequestDTO) {
        Owner owner = ownerMapper.fromOwnerRequestDTOtoOwner(ownerRequestDTO);
        ownerRepository.save(owner);

        return owner;
    }

    public Owner getOwnerById(UUID id) {
        if (ownerRepository.findById(id).isPresent()) {

            return ownerRepository.findById(id).get();
        }

        throw new EntityNotFoundException("Owner not found");
    }
}
