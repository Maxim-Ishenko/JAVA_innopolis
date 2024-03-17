package ru.innopolis.attestation03.services;

import ru.innopolis.attestation03.dto.TimeSlotDto;
import ru.innopolis.attestation03.enums.ResultsMessages;
import ru.innopolis.attestation03.models.TimeSlot;

import java.time.LocalDate;
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
     * @return TimeSlotDto
     */
    List<TimeSlotDto> findAllByDoctorId(Long doctorId);

    /**
     * Поиск всех свободных временных слотов по конкретному доктору
     * @param doctorId
     * @return List<TimeSlotDto>
     */
    List<TimeSlotDto> findAllAvailableByDoctorId(Long doctorId);

    /**
     * Поиск всех свободных временных слотов по конкретному доктору и в конкретном диапазоне дат
     * @param doctorId
     * @param to
     * @param from
     * @return List<TimeSlotDto>
     */
    List<TimeSlotDto> findAllAvailableByDoctorIdAndDateRange(Long doctorId, LocalDate to, LocalDate from);
}
