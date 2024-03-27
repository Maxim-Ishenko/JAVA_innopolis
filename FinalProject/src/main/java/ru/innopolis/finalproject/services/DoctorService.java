package ru.innopolis.finalproject.services;

import ru.innopolis.finalproject.dto.DoctorDto;
import ru.innopolis.finalproject.enums.ResultsMessages;

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
     * @param id - Идентификатор доктора
     * @return DoctorDto
     */
    DoctorDto findById(Long id);

    /**
     * Создание сущности доктора и запись его в БД
     *
     * @param doctor - Объект доктора
     * @return DoctorDto
     */
    DoctorDto create(DoctorDto doctor);

    /**
     * Обновление полей существующего в БД доктора
     *
     * @param doctorId - Идентификатор доктора
     * @param editedDoctorEntity - Обновленный объект доктора
     * @return DoctorDto
     */
    DoctorDto update(Long doctorId, DoctorDto editedDoctorEntity);

    /**
     * Удаление доктора из БД по идентификатору
     * @param id - Идентификатор доктора
     */
    void deleteById(Long id);

    /**
     * Удаление всех докторов из БД
     *
     * @return ResultsMessages
     */
    ResultsMessages deleteAll();

    /**
     * Обратимое удаление доктора из БД по идентификатору
     *
     * @param id - Идентификатор доктора
     */
    void softDeleteById(Long id);
}
