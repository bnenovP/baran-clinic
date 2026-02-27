package com.example.baranclinic.crm.domain.common.util;

import com.example.baranclinic.crm.domain.dto.common.DogSummaryDTO;
import com.example.baranclinic.crm.domain.dto.request.DogRequestDTO;
import com.example.baranclinic.crm.domain.dto.response.DogResponseDTO;
import com.example.baranclinic.crm.domain.entity.Dog;
import com.example.baranclinic.crm.domain.entity.Owner;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class DogMapper {

    public DogSummaryDTO mapDogToDogSummary(Dog dog) {
        return DogSummaryDTO.builder()
                .id(dog.getId())
                .name(dog.getName())
                .breed(dog.getBreed())
                .build();
    }

    public Dog mapDogSummaryDTOtoDog(DogRequestDTO dogRequestDTO, Owner owner) {
        return Dog.builder()
                .name(dogRequestDTO.getName())
                .microchipId(dogRequestDTO.getMicrochipId())
                .breed(dogRequestDTO.getBreed())
                .birthDate(dogRequestDTO.getBirthDate())
                .weight(dogRequestDTO.getWeight())
                .owner(owner)
                .build();
    }

    public DogResponseDTO mapDogToDogResponseDTO(Dog dog) {
        return DogResponseDTO.builder()
                .id(dog.getId())
                .name(dog.getName())
                .microchipId(dog.getMicrochipId())
                .breed(dog.getBreed())
                .birthDate(dog.getBirthDate())
                .weight(dog.getWeight())
                .ownerId(dog.getOwner().getId())
                .build();
    }
}
