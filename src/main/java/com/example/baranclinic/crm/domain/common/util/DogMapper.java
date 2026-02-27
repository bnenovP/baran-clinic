package com.example.baranclinic.crm.domain.common.util;

import com.example.baranclinic.crm.domain.dto.common.DogSummaryDTO;
import com.example.baranclinic.crm.domain.dto.request.DogRequestDTO;
import com.example.baranclinic.crm.domain.entity.Dog;
import com.example.baranclinic.crm.domain.entity.Owner;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class DogMapper {

    public DogSummaryDTO mapDogToDogSummary(Dog dog) {
        DogSummaryDTO dogSummaryDTO = DogSummaryDTO.builder()
                .id(dog.getId())
                .name(dog.getName())
                .breed(dog.getBreed())
                .build();

        return dogSummaryDTO;
    }

    public Dog mapDogSummaryDTOtoDog(DogRequestDTO dogRequestDTO, Owner owner) {
        Dog dog = Dog.builder()
                .name(dogRequestDTO.getName())
                .microchipId(dogRequestDTO.getMicrochipId())
                .breed(dogRequestDTO.getBreed())
                .birthDate(dogRequestDTO.getBirthDate())
                .weight(dogRequestDTO.getWeight())
                .owner(owner)
                .build();

        return dog;
    }
}
