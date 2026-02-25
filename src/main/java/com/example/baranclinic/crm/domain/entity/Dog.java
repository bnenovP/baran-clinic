package com.example.baranclinic.crm.domain.entity;

import com.example.baranclinic.clinical.domain.entity.MedicalRecordEntry;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
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

    private double weight;

    @OneToMany(mappedBy = "dog", cascade = CascadeType.ALL)
    private List<MedicalRecordEntry> medicalHistory;

    @ManyToOne
    @JoinColumn (name = "owner_id")
    private Owner owner;
}
