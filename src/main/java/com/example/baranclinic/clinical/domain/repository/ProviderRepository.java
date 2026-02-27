package com.example.baranclinic.clinical.domain.repository;

import com.example.baranclinic.clinical.domain.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, UUID> {
    Optional<Provider> findByLicenseNumber(String licenseNumber);
}
