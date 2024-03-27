package ru.innopolis.finalproject.services;

import ru.innopolis.finalproject.dto.TimeSlotDto;
import ru.innopolis.finalproject.enums.ResultsMessages;
import ru.innopolis.finalproject.models.TimeSlot;

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
     * @param id - Идентификатор временного слота
     * @return TimeSlot
     */
    TimeSlotDto findById(Long id);

    /**
     * Создание сущности окна записи и запись его в БД
     * @param doctorId - Идентификатор доктора
     * @param timeSlot - Идентификатор временного слота
     * @return TimeSlotDto
     */
    TimeSlotDto create(Long doctorId, TimeSlot timeSlot);

    /**
     * Обновление полей существующего в БД окна записи
     * @param timeSlotId - Идентификатор временного слота
     * @param timeSlot - Объект временного слота
     * @return TimeSlotDto
     */
    TimeSlotDto update(Long timeSlotId, TimeSlotDto timeSlot);

    /**
     * Удаление окна записи из БД по идентификатору
     * @param id - Идентификатор временного слота
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
     * @param id - Идентификатор временного слота
     */
    void softDeleteById(Long id);

    /**
     * Поиск всех временных слотов по конкретному доктору
     * @param doctorId - Идентификатор доктора
     * @return TimeSlotDto
     */
    List<TimeSlotDto> findAllByDoctorId(Long doctorId);

    /**
     * Поиск всех свободных временных слотов по конкретному доктору
     * @param doctorId - Идентификатор доктора
     * @return List<TimeSlotDto>
     */
    List<TimeSlotDto> findAllAvailableByDoctorId(Long doctorId);

    /**
     * Поиск всех свободных временных слотов по конкретному доктору и в конкретном диапазоне дат
     * @param doctorId - Идентификатор доктора
     * @param to - Врехняя граница целевого диапазона дат
     * @param from - Нижняя граница целевого диапазона дат
     * @return List<TimeSlotDto>
     */
    List<TimeSlotDto> findAllAvailableByDoctorIdAndDateRange(Long doctorId, LocalDate to, LocalDate from);
}
