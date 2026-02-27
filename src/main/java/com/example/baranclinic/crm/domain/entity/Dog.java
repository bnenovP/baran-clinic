package com.example.baranclinic.crm.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "dog_id", nullable = false)
    private UUID id;

    private String microchipId;

    @Column(name = "dog_name")
    private String name;

    private String breed;

    private LocalDate birthDate;

    @Setter
    private double weight;

    @ManyToOne
    @JoinColumn (name = "owner_id")
    private Owner owner;
}
