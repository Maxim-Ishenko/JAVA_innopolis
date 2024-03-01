package ru.innopolis.attestattion03.models;

import lombok.Getter;

@Getter
public enum Specialities {
    NEUROLOGIST("Neurologist"),
    PSYCHOLOGIST("Psychologist"),
    PASYCHIATRIST("Psychiatrist"),
    FUNCTIONAL_DIAGNOSTICIAN("Functional diagnostician"),
    CHIEF_PHYSICIAN("Chief physician");

    private final String speciality;

    Specialities(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return speciality;
    }
}
