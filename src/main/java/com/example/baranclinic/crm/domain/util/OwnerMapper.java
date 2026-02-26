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

    public OwnerResponseDTO fromOwnerToResponseOwnerDTO(Owner owner) {
        OwnerResponseDTO ownerResponseDTO = OwnerResponseDTO.builder()
                .id(owner.getId())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .phoneNumber(owner.getPhoneNumber())
                .email(owner.getEmail())
                .address(owner.getAddress())
                .dogs(owner.getDogs() != null ?
                        owner.getDogs()
                                .stream()
                                .map(dogMapper::mapDogToDogSummary).toList() : List.of())
                .build();

        return ownerResponseDTO;
    }

}
