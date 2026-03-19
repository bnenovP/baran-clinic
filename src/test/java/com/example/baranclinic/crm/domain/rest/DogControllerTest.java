package com.example.baranclinic.crm.domain.rest;

import com.example.baranclinic.crm.domain.dto.request.DogRequestDTO;
import com.example.baranclinic.crm.domain.dto.request.OwnerRequestDTO;
import com.example.baranclinic.crm.domain.dto.response.OwnerResponseDTO;
import com.example.baranclinic.crm.domain.service.OwnerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5432/baran_clinic_db",
        "spring.datasource.username=postgres",
        "spring.datasource.password=admin123"})
@AutoConfigureMockMvc
@Transactional
class DogControllerTest {

    private static final String CREATE_DOG_URL = "/api/v1/dogs";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OwnerService ownerService;

    @BeforeEach
    void setup() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void whenCreateNewDogWithExistingOwner_thenReturnCreated() throws Exception {
        OwnerRequestDTO owner = OwnerRequestDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123456789")
                .build();
        OwnerResponseDTO savedOwner = ownerService.registerOwner(owner);

        DogRequestDTO requestDTO = DogRequestDTO.builder()
                .breed("German Shepard")
                .name("Rex")
                .ownerId(savedOwner.getId())
                .microchipId("CHIP123")
                .birthDate(LocalDate.of(2024, 5, 20))
                .weight(30.5)
                .build();

        mockMvc.perform(post(CREATE_DOG_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenCreateNewDogWithoutExistingOwner_thenReturnBadRequest() throws Exception {
        DogRequestDTO requestDTO = DogRequestDTO.builder()
                .breed("German Shepard")
                .name("Rex")
                .ownerId(UUID.randomUUID())
                .microchipId("CHIP222")
                .birthDate(LocalDate.of(2024, 5, 20))
                .weight(30.5)
                .build();

        mockMvc.perform(post(CREATE_DOG_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenCreateNewDogWithExistingMicrochip_thenReturnBadRequest() throws Exception {
        OwnerRequestDTO owner = OwnerRequestDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("123456789")
                .build();
        OwnerResponseDTO savedOwner = ownerService.registerOwner(owner);

        DogRequestDTO requestDTO = DogRequestDTO.builder()
                .breed("German Shepard")
                .name("Rex")
                .ownerId(savedOwner.getId())
                .microchipId("CHIP123")
                .birthDate(LocalDate.of(2024, 5, 20))
                .weight(30.5)
                .build();

        mockMvc.perform(post(CREATE_DOG_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)));

        mockMvc.perform(post(CREATE_DOG_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }
}
