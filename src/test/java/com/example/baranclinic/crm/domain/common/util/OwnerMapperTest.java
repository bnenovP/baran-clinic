package com.example.baranclinic.crm.domain.common.util;

import com.example.baranclinic.crm.domain.dto.request.OwnerRequestDTO;
import com.example.baranclinic.crm.domain.dto.response.OwnerResponseDTO;
import com.example.baranclinic.crm.domain.entity.Owner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OwnerMapperTest {

    private final UUID ownerId = UUID.randomUUID();

    @InjectMocks
    private OwnerMapper ownerMapper;

    @Mock
    private DogMapper dogMapper;

    @Test
    void whenMapOwnerRequestDTOtoOwner_thenReturnOwner() {
        // Arrange
        OwnerRequestDTO ownerRequestDTO = createOwnerRequestDTO();

        // Act
        Owner owner = ownerMapper.fromOwnerRequestDTOtoOwner(ownerRequestDTO);

        // Assert
        assertEquals(owner.getFirstName(), ownerRequestDTO.getFirstName());
        assertEquals(owner.getLastName(), ownerRequestDTO.getLastName());
        assertEquals(owner.getAddress(), ownerRequestDTO.getAddress());
        assertEquals(owner.getPhoneNumber(), ownerRequestDTO.getPhoneNumber());
    }

    @Test
    void whenMapOwnerToOwnerResponseDTO_thenReturnOwnerResponseDTO() {
        // Arrange
        Owner owner = createOwner();

        // Act
        OwnerResponseDTO ownerResponseDTO = ownerMapper.fromOwnerToResponseOwnerDTO(owner);

        // Assert
        assertEquals(owner.getId(), ownerResponseDTO.getId());
        assertEquals(owner.getFirstName(), ownerResponseDTO.getFirstName());
        assertEquals(owner.getLastName(), ownerResponseDTO.getLastName());
        assertEquals(owner.getAddress(), ownerResponseDTO.getAddress());
        assertEquals(owner.getPhoneNumber(), ownerResponseDTO.getPhoneNumber());
    }

    private Owner createOwner() {
        return Owner.builder()
                .id(ownerId)
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("12345678")
                .email("johndoe@gmail.com")
                .build();
    }

    private OwnerRequestDTO createOwnerRequestDTO() {
        return OwnerRequestDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("12345678")
                .email("johndoe@gmail.com")
                .build();
    }
}