package ru.innopolis.attestation03.models;

import lombok.Getter;

@Getter
public enum Specialities {
    NEUROLOGIST("Невролог"),
    PSYCHOLOGIST("Психолог"),
    PASYCHIATRIST("Психиатр"),
    FUNCTIONAL_DIAGNOSTICIAN("Функциональный диагност"),
    CHIEF_PHYSICIAN("Глав. врач");

    private final String speciality;

    Specialities(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return speciality;
    }
}
