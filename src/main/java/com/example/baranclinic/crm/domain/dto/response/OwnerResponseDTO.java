package com.example.baranclinic.crm.domain.dto.response;

import com.example.baranclinic.crm.domain.dto.common.DogSummaryDTO;
import com.example.baranclinic.crm.domain.model.Address;
import lombok.Builder;
import lombok.Value;


import java.util.List;
import java.util.UUID;

@Value
@Builder
public class OwnerResponseDTO {

    UUID id;

    String firstName;

    String lastName;

    String phoneNumber;

    String email;

    Address address;

    List<DogSummaryDTO> dogs;
}
