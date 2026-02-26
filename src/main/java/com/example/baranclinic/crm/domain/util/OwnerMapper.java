package com.example.baranclinic.crm.domain.util;

import com.example.baranclinic.crm.domain.dto.request.OwnerRequestDTO;
import com.example.baranclinic.crm.domain.dto.response.OwnerResponseDTO;
import com.example.baranclinic.crm.domain.entity.Owner;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OwnerMapper {

    private final DogMapper dogMapper;

    public Owner fromOwnerRequestDTOtoOwner(OwnerRequestDTO ownerRequestDTO) {
        Owner owner = Owner.builder()
                .firstName(ownerRequestDTO.getFirstName())
                .lastName(ownerRequestDTO.getLastName())
                .phoneNumber(ownerRequestDTO.getPhoneNumber())
                .email(ownerRequestDTO.getEmail())
                .address(ownerRequestDTO.getAddress())
                .build();

        return owner;
    }

    public Owner fromOwnerResponseDTOtoOwner(OwnerResponseDTO ownerResponseDTO) {
        Owner owner = Owner.builder()
                .id(ownerResponseDTO.getId())
                .firstName(ownerResponseDTO.getFirstName())
                .lastName(ownerResponseDTO.getLastName())
                .phoneNumber(ownerResponseDTO.getPhoneNumber())
                .email(ownerResponseDTO.getEmail())
                .address(ownerResponseDTO.getAddress())
                .dogs(ownerResponseDTO.getDogs() != null ?
                        ownerResponseDTO.getDogs()
                                .stream()
                                .map(dogMapper::mapDogSummaryDTOtoDog).toList() : List.of())
                .build();

        return owner;
    }

}
