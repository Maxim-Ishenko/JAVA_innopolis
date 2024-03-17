package ru.innopolis.attestation03.services;

import ru.innopolis.attestation03.dto.AppointmentDto;
import ru.innopolis.attestation03.enums.ResultsMessages;
import ru.innopolis.attestation03.models.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    /**
     * Выгрузка всех приемов из БД
     * @return List<AppointmentDto>
     */
    List<AppointmentDto> findAll();

    /**
     * Поиск приема в БД по идентификатору
     * @param id - Идентификатор приема
     * @return AppointmentDto
     */
    AppointmentDto findById(Long id);

    /**
     * Создание сущности приема и запись его в БД
     * @param doctorId - Идентификатор доктора
     * @param patientId - Идентификатор пациента
     * @param timeSlotId - Идентификатор временного слота
     * @param appointment - Объект записи на прием
     * @return AppointmentDto
     */
    AppointmentDto create(
            Long doctorId,
            Long patientId,
            Long timeSlotId,
            Appointment appointment
    );

    /**
     * Обновление полей существующего в БД приема
     * @param doctorId - Идентификатор доктора
     * @param patientId - Идентификатор пациента
     * @param timeSlotId - Идентификатор временного слота
     * @param appointment - Объект записи на прием
     * @return AppointmentDto
     */
    AppointmentDto update(
            Long doctorId,
            Long patientId,
            Long timeSlotId,
            Appointment appointment
    );

    /**
     * Удаление приема из БД по идентификатору
     * @param id - Идентификатор приема
     */
    void deleteById(Long id);

    /**
     * Удаление всех приемов из БД
     * @return ResultsMessages
     */
    ResultsMessages deleteAll();

    /**
     * Обратимое удаление приема из БД по идентификатору
     *
     * @param id - Идентификатор приема
     */
    void softDeleteById(Long id);

    /**
     * Поиск всех приемов по конкретному доктору
     * @param doctorId - Идентификатор доктора
     * @return AppointmentDto
     */
    List<AppointmentDto> findAllByDoctorId(Long doctorId);

    /**
     * Поиск всех приемов по конкретному пациенту
     * @param patientId - Идентификатор пациента
     * @return AppointmentDto
     */
    List<AppointmentDto> findAllByPatientId(Long patientId);

    /**
     * Поиск приема в БД по идентификатору временного слота
     * @param timeSlotId - Идентификатор временного слота
     * @return AppointmentDto
     */
    AppointmentDto findByTimeSlotId(Long timeSlotId);

    /**
     * Поиск приема в БД по идентификатору временного слота
     * @param doctorId - Идентификатор доктора
     * @param localDate - Целевая дата приема
     * @return AppointmentDto
     */
    AppointmentDto findAllByDoctorIdAndDate(Long doctorId, LocalDate localDate);
}
