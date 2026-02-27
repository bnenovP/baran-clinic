package com.example.baranclinic.crm.domain.service;

import com.example.baranclinic.crm.domain.common.exception.DogAlreadyExistsException;
import com.example.baranclinic.crm.domain.dto.request.DogRequestDTO;
import com.example.baranclinic.crm.domain.dto.response.DogResponseDTO;
import com.example.baranclinic.crm.domain.entity.Dog;
import com.example.baranclinic.crm.domain.repository.DogRepository;
import com.example.baranclinic.crm.domain.common.util.DogMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class DogService {

    private final DogRepository dogRepository;

    private final DogMapper dogMapper;

    @Transactional
    public DogResponseDTO registerDog(DogRequestDTO dogRequestDTO, UUID ownerId) {
        Optional<Dog> existingDog = dogRepository.findByMicrochipId(dogRequestDTO.getMicrochipId());

        if (existingDog.isPresent()) {
            throw new DogAlreadyExistsException("Dog with microchip ID " + dogRequestDTO.getMicrochipId() + " already exists");
        }

        dogMapper.mapDogSummaryDTOtoDog(dogRequestDTO);

        dogRepository.save();
    }
}
