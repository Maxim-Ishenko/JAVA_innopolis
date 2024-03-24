package ru.innopolis.finalproject.enums;

import lombok.Getter;

@Getter
public enum ResultsMessages {
    DOCTOR_NOT_FOUND("Доктора с таким идентификатором не существует"),
    DOCTOR_LIST_REQ_ERR("Во время запроса списка докторов произошла ошибка"),
    DOCTORS_LIST_REMOVE_ERROR("Во время очищения списка докторов произошла ошибка"),
    DOCTORS_LIST_REMOVE_SUCCESS("Список докторов очищен"),
    DOCTOR_CREATING_ERROR("Во время создания доктора произошла ошибка"),
    DOCTOR_ID_ALREADY_EXISTS_ERROR("Доктор с таким идентификатором уже существует"),
    DOCTOR_UPDATING_ERROR("Во время обновления данных доктора произошла ошибка"),

    PATIENT_NOT_FOUND("Пациента с таким идентификатором не существует"),
    PATIENT_LIST_REQ_ERR("Во время запроса списка пациентов произошла ошибка"),
    PATIENTS_LIST_REMOVE_ERROR("Во время очищения списка пациентов произошла ошибка"),
    PATIENTS_LIST_REMOVE_SUCCESS("Список пациентов очищен"),
    PATIENT_CREATING_ERROR("Во время создания пациента произошла ошибка"),
    PATIENT_ID_ALREADY_EXISTS_ERROR("Пациент с таким идентификатором уже существует"),
    PATIENT_UPDATING_ERROR("Во время обновления данных пациента произошла ошибка"),

    TIMESLOT_NOT_FOUND("Времени записи с таким идентификатором не существует"),
    TIMESLOT_LIST_REQ_ERR("Во время запроса списка окон записи произошла ошибка"),
    TIMESLOTS_LIST_REMOVE_ERROR("Во время очищения списка окон записи произошла ошибка"),
    TIMESLOTS_LIST_REMOVE_SUCCESS("Список окон записи очищен"),
    TIMESLOT_CREATING_ERROR("Во время создания окона записи произошла ошибка"),
    TIMESLOT_ID_ALREADY_EXISTS_ERROR("Окно записи с таким идентификатором уже существует"),
    TIMESLOT_UPDATING_ERROR("Во время обновления данных окна записи произошла ошибка"),

    APPOINTMENT_NOT_FOUND("Прием с таким идентификатором не существует"),
    APPOINTMENT_LIST_REQ_ERR("Во время запроса списка приемов произошла ошибка"),
    APPOINTMENTS_LIST_REMOVE_ERROR("Во время очищения списка приемов произошла ошибка"),
    APPOINTMENTS_LIST_REMOVE_SUCCESS("Список приемов очищен"),
    APPOINTMENT_CREATING_ERROR("Во время создания приема произошла ошибка"),
    APPOINTMENT_ID_ALREADY_EXISTS_ERROR("Прием с таким идентификатором уже существует"),
    APPOINTMENT_UPDATING_ERROR("Во время обновления данных о приеме произошла ошибка");

    private final String message;

    ResultsMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
