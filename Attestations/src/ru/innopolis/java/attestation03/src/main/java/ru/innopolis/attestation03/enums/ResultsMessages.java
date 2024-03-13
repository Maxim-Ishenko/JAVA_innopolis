package ru.innopolis.attestation03.enums;

import lombok.Getter;

@Getter
public enum ResultsMessages {
    DOCTOR_NOT_FOUND("Доктора с таким идентификатором не существует"),
    DOCTOR_LIST_REQ_ERR("Во время запроса списка докторов произошла ошибка"),
    PATIENT_NOT_FOUND("Пациента с таким идентификатором не существует"),
    PATIENT_LIST_REQ_ERR("Во время запроса списка пациентов произошла ошибка"),
    DOCTORS_LIST_REMOVE_ERROR("Во время очищения списка докторов произошла ошибка"),
    DOCTORS_LIST_REMOVE_SUCCESS("Список докторов очищен"),
    PATIENTS_LIST_REMOVE_ERROR("Во время очищения списка пациентов произошла ошибка"),
    PATIENTS_LIST_REMOVE_SUCCESS("Список пациентов очищен"),
    DOCTOR_CREATING_ERROR("Во время создания доктора произошла ошибка"),
    PATIENT_CREATING_ERROR("Во время создания пациента произошла ошибка"),
    DOCTOR_ID_ALREADY_EXISTS_ERROR("Доктор с таким идентификатором уже существует"),
    PATIENT_ID_ALREADY_EXISTS_ERROR("Пациенто с таким идентификатором уже существует"),
    DOCTOR_UPDATING_ERROR("Во время обновления данных доктора произошла ошибка"),
    PATIENT_UPDATING_ERROR("Во время обновления данных пациента произошла ошибка");

    private final String message;

    ResultsMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
