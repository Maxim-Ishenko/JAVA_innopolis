package ru.innopolis.attestation03.services.impl;

import ru.innopolis.attestation03.dto.AppointmentDto;
import ru.innopolis.attestation03.enums.ResultsMessages;
import ru.innopolis.attestation03.models.Appointment;
import ru.innopolis.attestation03.services.AppointmentService;

import java.time.LocalDate;
import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {
    /**
     * @return List<Appointment>
     */
    @Override
    public List<AppointmentDto> findAll() {
        return null;
    }

    /**
     * @param id - Идентификатор приема
     * @return AppointmentDto
     */
    @Override
    public AppointmentDto findById(Long id) {
        return null;
    }

    /**
     * @param doctorId    - Идентификатор доктора
     * @param patientId   - Идентификатор пациента
     * @param timeSlotId  - Идентификатор временного слота
     * @param appointment - Объект записи на прием
     * @return AppointmentDto
     */
    @Override
    public AppointmentDto create(Long doctorId, Long patientId, Long timeSlotId, Appointment appointment) {
        return null;
    }

    /**
     * @param doctorId    - Идентификатор доктора
     * @param patientId   - Идентификатор пациента
     * @param timeSlotId  - Идентификатор временного слота
     * @param appointment - Объект записи на прием
     * @return AppointmentDto
     */
    @Override
    public AppointmentDto update(Long doctorId, Long patientId, Long timeSlotId, Appointment appointment) {
        return null;
    }

    /**
     * @param id - Идентификатор приема
     */
    @Override
    public void deleteById(Long id) {

    }

    /**
     * @return ResultsMessages
     */
    @Override
    public ResultsMessages deleteAll() {
        return null;
    }

    /**
     * @param id - Идентификатор приема
     */
    @Override
    public void softDeleteById(Long id) {

    }

    /**
     * @param doctorId - Идентификатор доктора
     * @return List<AppointmentDto>
     */
    @Override
    public List<AppointmentDto> findAllByDoctorId(Long doctorId) {
        return null;
    }

    /**
     * @param patientId - Идентификатор пациента
     * @return List<AppointmentDto>
     */
    @Override
    public List<AppointmentDto> findAllByPatientId(Long patientId) {
        return null;
    }

    /**
     * @param timeSlotId - Идентификатор временного слота
     * @return AppointmentDto
     */
    @Override
    public AppointmentDto findByTimeSlotId(Long timeSlotId) {
        return null;
    }

    /**
     * @param doctorId  - Идентификатор доктора
     * @param localDate - Целевая дата приема
     * @return AppointmentDto
     */
    @Override
    public AppointmentDto findAllByDoctorIdAndDate(Long doctorId, LocalDate localDate) {
        return null;
    }
}
