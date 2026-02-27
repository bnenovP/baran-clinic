package com.example.baranclinic.crm.domain.service;

import com.example.baranclinic.crm.domain.common.util.DogMapper;
import com.example.baranclinic.crm.domain.dto.request.DogRequestDTO;
import com.example.baranclinic.crm.domain.dto.response.DogResponseDTO;
import com.example.baranclinic.crm.domain.entity.Dog;
import com.example.baranclinic.crm.domain.entity.Owner;
import com.example.baranclinic.crm.domain.repository.DogRepository;
import com.example.baranclinic.crm.domain.repository.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DogServiceTest {

    @Mock
    private DogRepository dogRepository;

    @Mock
    private DogMapper dogMapper;

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private DogService dogService;

    @Test
    void registerDog_ShouldSaveAndReturnDog() {
        // Arrange
        UUID ownerId = UUID.randomUUID();
        DogRequestDTO request = createDogRequestDTO(ownerId);
        Owner owner = Owner.builder().id(ownerId).build();
        Dog dog = Dog.builder().name("Rex").owner(owner).build();
        DogResponseDTO response = createDogResponseDTO(ownerId);

        when(dogRepository.findByMicrochipId(request.getMicrochipId())).thenReturn(Optional.empty());
        when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(owner));
        when(dogMapper.mapDogSummaryDTOtoDog(request, owner)).thenReturn(dog);
        when(dogRepository.save(dog)).thenReturn(dog);
        when(dogMapper.mapDogToDogResponseDTO(dog)).thenReturn(response);

        // Act
        DogResponseDTO result = dogService.registerDog(request, ownerId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Rex");
        verify(dogRepository).save(any(Dog.class));
    }

    private DogRequestDTO createDogRequestDTO(UUID ownerId) {
        return DogRequestDTO.builder()
                .name("Rex")
                .microchipId("123456789")
                .breed("Labrador")
                .birthDate(LocalDate.now().minusYears(2))
                .weight(25.0)
                .ownerId(ownerId)
                .build();
    }

    private DogResponseDTO createDogResponseDTO(UUID ownerId) {
        return DogResponseDTO.builder()
                .id(UUID.randomUUID())
                .name("Rex")
                .microchipId("123456789")
                .breed("Labrador")
                .birthDate(LocalDate.now().minusYears(2))
                .weight(25.0)
                .ownerId(ownerId)
                .build();
    }
}
