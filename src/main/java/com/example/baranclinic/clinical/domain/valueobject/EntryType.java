package com.example.baranclinic.clinical.domain.valueobject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntryType {
    VACCINATION("Vaccination", "Shield"),
    SURGERY("Surgical Intervention", "Hospital"),
    CHECKUP("Routine Checkup", "Urgent Checkup"),
    MEDICATION("Prescription/Medication", "Pill");

    private final String displayName;
    private final String icon; // Feature development
}
