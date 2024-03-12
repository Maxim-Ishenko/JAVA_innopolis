package ru.innopolis.attestation03.services;

import ru.innopolis.attestation03.models.Patient;

import java.io.IOException;
import java.util.List;

public interface PatientService {
    /**
     * Выгрузка всех пациентов из БД
     * @return List<Patient>
     */
    List<Patient> findAll();

    /**
     * Поиск пациента в БД по идентификатору
     * @return Patient
     */
    Patient findById(String id);

    /**
     * Создание сущности пациента и запись его в БД
     * @return void
     */
    void create(Patient patient);

    /**
     * Обновление полей существующего в БД пациента
     * @return void
     */
    void update(Patient patient);

    /**
     * Удаление пациента из БД по идентификатору
     * @return void
     */
    void deleteById(String id);

    /**
     * Удаление всех пациентов из БД
     * @return void
     */
    void deleteAll() throws IOException;
}
