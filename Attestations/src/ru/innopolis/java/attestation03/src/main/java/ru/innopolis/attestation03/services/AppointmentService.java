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
            AppointmentDto appointment
    );

    /**
     * Обновление полей существующего в БД приема
     * @param appointmentId  - Идентификатор приема
     * @param editedAppointmentEntity - Объект записи на прием
     * @return AppointmentDto
     */
    AppointmentDto update(
            Long appointmentId,
            AppointmentDto editedAppointmentEntity
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
    Appointment findByTimeSlotId(Long timeSlotId);

    /**
     * Поиск приема в БД по идентификатору доктора и целевой дате приема
     * @param doctorId - Идентификатор доктора
     * @param localDate - Целевая дата приема
     * @return List<Appointment>
     */
    List<Appointment> findAllByDoctorIdAndDate(Long doctorId, LocalDate localDate);

    /**
     * Поиск приема в БД по идентификатору пациента и целевой дате приема
     * @param patientId - Идентификатор пациента
     * @param localDate - Целевая дата приема
     * @return List<Appointment>
     */
    List<Appointment> findAllByPatientIdAndDate(Long patientId, LocalDate localDate);

    /**
     * Поиск приема в БД по идентификатору доктора и идентификатору пациента
     * @param doctorId - Идентификатор пациента
     * @param patientId - Целевая дата приема
     * @return List<Appointment>
     */
    List<Appointment> findAllByDoctorIdAndPatientId(Long doctorId, Long patientId);
}
