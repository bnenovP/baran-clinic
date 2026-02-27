package com.example.baranclinic.crm.domain.service;
    
import com.example.baranclinic.crm.domain.common.exception.DogAlreadyExistsException;
import com.example.baranclinic.crm.domain.common.util.DogMapper;
import com.example.baranclinic.crm.domain.dto.request.DogRequestDTO;
import com.example.baranclinic.crm.domain.dto.response.DogResponseDTO;
import com.example.baranclinic.crm.domain.entity.Dog;
import com.example.baranclinic.crm.domain.entity.Owner;
import com.example.baranclinic.crm.domain.repository.DogRepository;
import com.example.baranclinic.crm.domain.repository.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
    
import java.util.UUID;
    
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DogService {
    
    private final DogRepository dogRepository;
    
    private final DogMapper dogMapper;
    
    private final OwnerRepository ownerRepository;
    
    @Transactional
    public DogResponseDTO registerDog(DogRequestDTO dogRequestDTO, UUID ownerId) {
        dogRepository.findByMicrochipId(dogRequestDTO.getMicrochipId())
                .ifPresent(dog -> {
                    throw new DogAlreadyExistsException("Dog with microchip ID " + dogRequestDTO.getMicrochipId() + " already exists");
                });
    
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with ID: " + ownerId));
            
        Dog dog = dogMapper.mapDogSummaryDTOtoDog(dogRequestDTO, owner);
        Dog savedDog = dogRepository.save(dog);
    
        return dogMapper.mapDogToDogResponseDTO(savedDog);
    }
}
