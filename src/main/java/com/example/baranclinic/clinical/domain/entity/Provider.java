package com.example.baranclinic.clinical.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "providers")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "provider_id", nullable = false)
    private UUID id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Column(unique = true, updatable = false)
    private String licenseNumber;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    private void providerDeactivate() {
        this.active = false;
    }
}
