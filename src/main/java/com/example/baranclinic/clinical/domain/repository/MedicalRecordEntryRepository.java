package com.example.baranclinic.clinical.domain.repository;

import com.example.baranclinic.clinical.domain.entity.MedicalRecordEntry;
import com.example.baranclinic.clinical.domain.valueobject.EntryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedicalRecordEntryRepository extends JpaRepository<MedicalRecordEntry, UUID> {

    List<MedicalRecordEntry> findByDogId(UUID dogId);

    List<MedicalRecordEntry> findByType(EntryType type);
}