package com.example.baranclinic.crm.domain.common.util;

import com.example.baranclinic.crm.domain.dto.common.DogSummaryDTO;
import com.example.baranclinic.crm.domain.dto.request.DogRequestDTO;
import com.example.baranclinic.crm.domain.dto.response.DogResponseDTO;
import com.example.baranclinic.crm.domain.entity.Dog;
import com.example.baranclinic.crm.domain.entity.Owner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DogMapperTest {

    private final UUID dogId = UUID.randomUUID();

    private final UUID ownerId = UUID.randomUUID();

    @InjectMocks
    private DogMapper mapper;

    @Test
    void whenMapDogToDogSummaryDTO_thenReturnDogSummaryDTO() {
        // Arrange
        Dog dog = createDog(createOwner());

        // Act
        DogSummaryDTO dogSummaryDTO = mapper.mapDogToDogSummary(dog);

        // Assert
        assertEquals(dog.getId(), dogSummaryDTO.getId());
        assertEquals(dog.getName(), dogSummaryDTO.getName());
        assertEquals(dog.getBreed(), dogSummaryDTO.getBreed());
    }

    @Test
    void whenMapDogSummaryDTOtoDog_thenReturnDog() {
        // Arrange
        Owner owner = createOwner();
        DogRequestDTO dogRequestDTO = createDogRequestDTO();

        // Act
        Dog dog = mapper.mapDogRequestDTOtoDog(dogRequestDTO, owner);

        // Assert
        assertEquals(dog.getBreed(), dogRequestDTO.getBreed());
        assertEquals(dog.getName(), dogRequestDTO.getName());
        assertEquals(dog.getMicrochipId(), dogRequestDTO.getMicrochipId());
        assertEquals(dog.getBirthDate(), dogRequestDTO.getBirthDate());
        assertEquals(dog.getWeight(), dogRequestDTO.getWeight());
    }

    @Test
    void whenMapDogToDogResponseDTO_thenReturnDogResponseDTO() {
        // Arrange
        Dog dog = createDog(createOwner());

        // Act
        DogResponseDTO responseDTO = mapper.mapDogToDogResponseDTO(dog);

        // Assert
        assertEquals(dog.getId(), responseDTO.getId());
        assertEquals(dog.getName(), responseDTO.getName());
        assertEquals(dog.getMicrochipId(), responseDTO.getMicrochipId());
        assertEquals(dog.getBirthDate(), responseDTO.getBirthDate());
        assertEquals(dog.getWeight(), responseDTO.getWeight());
        assertEquals(dog.getBreed(), responseDTO.getBreed());
    }

    private Dog createDog(Owner owner) {
        return Dog.builder()
                .id(dogId)
                .owner(owner)
                .breed("German Shepard")
                .name("Rex")
                .microchipId("CHIP123")
                .build();
    }

    private Owner createOwner() {
        return Owner.builder()
                .id(ownerId)
                .build();
    }

    private DogRequestDTO createDogRequestDTO() {
        return DogRequestDTO.builder()
                .name("Rex")
                .microchipId("CHIP123")
                .birthDate(LocalDate.now().minusMonths(2))
                .breed("German Shepard")
                .ownerId(ownerId)
                .weight(2.5)
                .build();
    }
}