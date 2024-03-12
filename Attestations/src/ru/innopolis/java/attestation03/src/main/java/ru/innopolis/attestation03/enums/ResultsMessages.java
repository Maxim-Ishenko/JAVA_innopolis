package ru.innopolis.attestation03.enums;

import lombok.Getter;

@Getter
public enum ResultsMessages {
    DOCTOR_NOT_FOUND(10, "Доктора с таким идентификатором не существует"),
    DOCTOR_LIST_REQ_ERR(11, "Во время запроса списка докторов произошла ошибка"),
    DOCTORS_LIST_REMOVE_ERROR(20, "Во время очищения списка докторов произошла ошибка"),
    DOCTORS_LIST_REMOVE_SUCCESS(21, "Список докторов очищен"),
    DOCTOR_CREATING_ERROR(30, "Во время создания доктора произошла ошибка"),
    DOCTOR_ID_ALREADY_EXISTS_ERROR(31, "Доктор с таким идентификатором уже существует"),
    DOCTOR_UPDATING_ERROR(40, "Во время обновления данных доктора произошла ошибка");

    private final Integer code;
    private final String message;

    ResultsMessages(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
