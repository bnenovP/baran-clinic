package com.example.baranclinic.crm.domain.service;

import com.example.baranclinic.crm.domain.dto.request.OwnerRequestDTO;
import com.example.baranclinic.crm.domain.dto.response.OwnerResponseDTO;
import com.example.baranclinic.crm.domain.entity.Owner;
import com.example.baranclinic.crm.domain.model.Address;
import com.example.baranclinic.crm.domain.repository.OwnerRepository;
import com.example.baranclinic.crm.domain.common.util.OwnerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private OwnerMapper ownerMapper;

    @InjectMocks
    private OwnerService ownerService;

    @Test
    public void saveOwner() {
        // Arrange
        Owner owner = createOwner();
        OwnerRequestDTO ownerRequestDTO = createOwnerRequestDTO();
        OwnerResponseDTO ownerResponseDTO = createOwnerResponseDTO();

        when(ownerMapper.fromOwnerRequestDTOtoOwner(ownerRequestDTO)).thenReturn(owner);
        when(ownerRepository.save(owner)).thenReturn(owner);
        when(ownerMapper.fromOwnerToResponseOwnerDTO(owner)).thenReturn(ownerResponseDTO);

        // Act
        OwnerResponseDTO savedOwner = ownerService.registerOwner(ownerRequestDTO);

        // Assert
        assertEquals(savedOwner.getId(), ownerResponseDTO.getId());
        assertEquals(savedOwner.getFirstName(), ownerResponseDTO.getFirstName());
        assertEquals(savedOwner.getLastName(), ownerResponseDTO.getLastName());
        assertEquals(savedOwner.getPhoneNumber(), ownerResponseDTO.getPhoneNumber());
        assertEquals(savedOwner.getEmail(), ownerResponseDTO.getEmail());
    }

    @Test
    public void getOwnerById() {
        // Arrange
        Owner owner = createOwner();
        OwnerResponseDTO ownerResponseDTO = createOwnerResponseDTO();

        when(ownerRepository.findById(owner.getId())).thenReturn(java.util.Optional.of(owner));
        when(ownerMapper.fromOwnerToResponseOwnerDTO(owner)).thenReturn(ownerResponseDTO);

        // Act
        OwnerResponseDTO foundOwner = ownerService.getOwnerById(owner.getId());

        // Assert
        assertEquals(foundOwner.getId(), ownerResponseDTO.getId());
    }

    private Owner createOwner() {
        Address address = new Address("Berlin", "Street 1");

        return Owner.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123456789")
                .email("johndoe@gmail.com")
                .address(address)
                .build();
    }

    private OwnerRequestDTO createOwnerRequestDTO() {
        Address address = new Address("Berlin", "Street 1");

        return OwnerRequestDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123456789")
                .email("johndoe@gmail.com")
                .address(address)
                .build();
    }

    private OwnerResponseDTO createOwnerResponseDTO() {
        return OwnerResponseDTO.builder()
                .id(UUID.randomUUID())
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123456789")
                .email("johndoe@gmail.com")
                .address(new Address("Berlin", "Street 1"))
                .build();
    }
}