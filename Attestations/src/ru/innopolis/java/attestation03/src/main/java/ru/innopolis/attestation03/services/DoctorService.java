package ru.innopolis.attestation03.services;

import ru.innopolis.attestation03.dto.DoctorDto;
import ru.innopolis.attestation03.enums.ResultsMessages;

import java.util.List;

public interface DoctorService {
    /**
     * Выгрузка всех докторов из БД
     * @return List<Doctor>
     */
    List<DoctorDto> findAll();

    /**
     * Поиск доктора в БД по идентификатору
     *
     * @param id
     * @return DoctorDto
     */
    DoctorDto findById(Long id);

    /**
     * Создание сущности доктора и запись его в БД
     *
     * @param doctor
     */
    DoctorDto create(DoctorDto doctor);

    /**
     * Обновление полей существующего в БД доктора
     * * @param doctor
     *
     * @return DoctorDto
     */
    DoctorDto update(Long doctorId, DoctorDto editedDoctorEntity);

    /**
     * Удаление доктора из БД по идентификатору
     * @param id
     * @return void
     */
    void deleteById(Long id);

    /**
     * Удаление всех докторов из БД
     *
     * @return void
     */
    ResultsMessages deleteAll();

    /**
     * Обратимое удаление доктора из БД по идентификатору
     *
     * @param id
     * @return void
     */
    void softDeleteById(Long id);
}
