package ru.innopolis.attestation03.services;

import ru.innopolis.attestation03.dto.PatientDto;
import ru.innopolis.attestation03.enums.ResultsMessages;

import java.util.List;

public interface PatientService {
    /**
     * Выгрузка всех пациентов из БД
     * @return List<PatientDto>
     */
    List<PatientDto> findAll();

    /**
     * Поиск пациента в БД по идентификатору
     * @param id - Идентификатор пациента
     * @return PatientDto
     */
    PatientDto findById(Long id);

    /**
     * Создание сущности пациента и запись его в БД
     * @param patient - Объект пациента
     * @return PatientDto
     */
    PatientDto create(PatientDto patient);

    /**
     * Обновление полей существующего в БД пациента
     * @param patientId - Идентификатор пациента
     * @param editedPatientEntity - Обновленный объект пациента
     * @return PatientDto
     */
    PatientDto update(Long patientId, PatientDto editedPatientEntity);

    /**
     * Удаление пациента из БД по идентификатору
     *
     * @param id - Идентификатор пациента
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
     * @param id - Идентификатор пациента
     */
    void softDeleteById(Long id);
}
