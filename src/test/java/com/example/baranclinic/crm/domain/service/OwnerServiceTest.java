package com.example.baranclinic.crm.domain.service;

import com.example.baranclinic.crm.domain.dto.request.OwnerRequestDTO;
import com.example.baranclinic.crm.domain.entity.Owner;
import com.example.baranclinic.crm.domain.model.Address;
import com.example.baranclinic.crm.domain.repository.OwnerRepository;
import com.example.baranclinic.crm.domain.util.OwnerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

        when(ownerMapper.fromOwnerRequestDTOtoOwner(ownerRequestDTO)).thenReturn(owner);
        when(ownerRepository.save(owner)).thenReturn(owner);

        // Act
        Owner savedOwner = ownerService.registerOwner(ownerRequestDTO);

        // Assert
        assertEquals(savedOwner.getId(), owner.getId());
        assertEquals(savedOwner.getFirstName(), owner.getFirstName());
        assertEquals(savedOwner.getLastName(), owner.getLastName());
        assertEquals(savedOwner.getPhoneNumber(), owner.getPhoneNumber());
        assertEquals(savedOwner.getEmail(), owner.getEmail());
    }

    @Test
    public void getOwnerById() {
        // Arrange
        Owner owner = createOwner();

        when(ownerRepository.findById(owner.getId())).thenReturn(java.util.Optional.of(owner));

        // Act
        Owner foundOwner = ownerService.getOwnerById(owner.getId());

        // Assert
        assertEquals(foundOwner.getId(), owner.getId());
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
}