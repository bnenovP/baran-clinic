package com.example.baranclinic.crm.domain.service;
    
import com.example.baranclinic.crm.domain.dto.request.OwnerRequestDTO;
import com.example.baranclinic.crm.domain.dto.response.OwnerResponseDTO;
import com.example.baranclinic.crm.domain.entity.Owner;
import com.example.baranclinic.crm.domain.repository.OwnerRepository;
import com.example.baranclinic.crm.domain.common.util.OwnerMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
    
import java.util.UUID;
    
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OwnerService {
    
    private final OwnerMapper ownerMapper;
        
    private final OwnerRepository ownerRepository;
        
    @Transactional
    public OwnerResponseDTO registerOwner(OwnerRequestDTO ownerRequestDTO) {
        Owner owner = ownerMapper.fromOwnerRequestDTOtoOwner(ownerRequestDTO);
        ownerRepository.save(owner);
    
        return ownerMapper.fromOwnerToResponseOwnerDTO(owner);
    }
    
    public OwnerResponseDTO getOwnerById(UUID id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with ID: " + id));
        return ownerMapper.fromOwnerToResponseOwnerDTO(owner);
    }
}
