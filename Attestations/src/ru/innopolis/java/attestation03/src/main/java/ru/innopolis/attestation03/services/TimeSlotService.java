package ru.innopolis.attestation03.services;

import ru.innopolis.attestation03.dto.AddTimeSlotDto;
import ru.innopolis.attestation03.dto.TimeSlotDto;
import ru.innopolis.attestation03.enums.ResultsMessages;
import ru.innopolis.attestation03.models.TimeSlot;

import java.util.List;

public interface TimeSlotService {
    /**
     * Выгрузка всех окон записи из БД
     * @return List<TimeSlot>
     */
    List<TimeSlotDto> findAll();

    /**
     * Поиск окна записи в БД по идентификатору
     * @return TimeSlot
     */
    TimeSlotDto findById(Long id);

    /**
     * Создание сущности окна записи и запись его в БД
     * @return TimeSlotDto
     */
//    TimeSlotDto create(AddTimeSlotDto timeSlot);
    TimeSlotDto create(Long doctorId, TimeSlot timeSlot);
    /**
     * Обновление полей существующего в БД окна записи
     * @return TimeSlotDto
     */
    TimeSlotDto update(Long timeSlotId, TimeSlotDto timeSlot);

    /**
     * Удаление окна записи из БД по идентификатору
     * @return void
     */
    void deleteById(Long id);

    /**
     * Удаление всех окон записи из БД
     * @return ResultsMessages
     */
    ResultsMessages deleteAll();

    /**
     * Обратимое удаление доктора из БД по идентификатору
     *
     * @param id
     * @return void
     */
    void softDeleteById(Long id);

    /**
     * Поиск всех временных слотов по конкретному доктору
     * @return TimeSlot
     */
    List<TimeSlotDto> findAllByDoctorId(Long doctorId);

    /**
     * Поиск всех свободных временных слотов по конкретному доктору
     * @return List<TimeSlot>
     */
    List<TimeSlotDto> findAllAvailableByDoctorId(Long doctorId);
}
