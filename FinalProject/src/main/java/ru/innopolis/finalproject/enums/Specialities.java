package ru.innopolis.finalproject.enums;

import lombok.Getter;

@Getter
public enum Specialities {
    NEUROLOGIST(1, "Невролог"),
    PSYCHOLOGIST(2, "Психолог"),
    PASYCHIATRIST(3, "Психиатр"),
    FUNCTIONAL_DIAGNOSTICIAN(4, "Функциональный диагност"),
    CHIEF_PHYSICIAN(0, "Глав. врач");

    private final Integer code;
    private final String speciality;

    Specialities(Integer code, String speciality) {
        this.code = code;
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return speciality;
    }
}
