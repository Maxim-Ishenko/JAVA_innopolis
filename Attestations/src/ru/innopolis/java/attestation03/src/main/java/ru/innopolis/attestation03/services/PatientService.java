package ru.innopolis.attestation03.services;

import ru.innopolis.attestation03.dto.PatientDto;
import ru.innopolis.attestation03.enums.ResultsMessages;

import java.io.IOException;
import java.util.List;

public interface PatientService {
    /**
     * Выгрузка всех пациентов из БД
     * @return List<Patient>
     */
    List<PatientDto> findAll();

    /**
     * Поиск пациента в БД по идентификатору
     * @return PatientDto
     */
    PatientDto findById(Long id);

    /**
     * Создание сущности пациента и запись его в БД
     * @return PatientDto
     */
    PatientDto create(PatientDto patient);

    /**
     * Обновление полей существующего в БД пациента
     * @return PatientDto
     */
    PatientDto update(Long patientId, PatientDto editedPatientEntity);

    /**
     * Удаление пациента из БД по идентификатору
     * @return void
     */
    void deleteById(Long id);

    /**
     * Удаление всех пациентов из БД
     *
     * @return ResultsMessages
     */
    ResultsMessages deleteAll();


    /**
     * Обратимое удаление пациентов из БД по идентификатору
     *
     * @param id
     * @return void
     */
    void softDeleteById(Long id);
}
