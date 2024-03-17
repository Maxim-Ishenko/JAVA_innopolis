package ru.innopolis.attestation03.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.attestation03.dto.AppointmentDto;
import ru.innopolis.attestation03.dto.DoctorDto;
import ru.innopolis.attestation03.dto.TimeSlotDto;
import ru.innopolis.attestation03.enums.ResultsMessages;
import ru.innopolis.attestation03.exceptions.CustomException;
import ru.innopolis.attestation03.exceptions.NotFoundException;
import ru.innopolis.attestation03.models.Appointment;
import ru.innopolis.attestation03.models.Doctor;
import ru.innopolis.attestation03.models.Patient;
import ru.innopolis.attestation03.models.TimeSlot;
import ru.innopolis.attestation03.repositories.AppointmentRepository;
import ru.innopolis.attestation03.repositories.DoctorRepository;
import ru.innopolis.attestation03.repositories.PatientRepository;
import ru.innopolis.attestation03.repositories.TimeSlotRepository;
import ru.innopolis.attestation03.services.AppointmentService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Supplier;

import static ru.innopolis.attestation03.dto.AppointmentDto.from;

@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    /**
     * @return List<Appointment>
     */
    @Override
    public List<AppointmentDto> findAll() {
        try {
            return from(appointmentRepository.findAllNotRemovedAppointments());
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param id - Идентификатор приема
     * @return AppointmentDto
     */
    @Override
    public AppointmentDto findById(Long id) {
        try {
            Appointment targetAppointment = appointmentRepository.findById(id).orElseThrow(NotFoundException::new);

            if (targetAppointment.getHasRemoved()) {
                throw new NotFoundException();
            }

            return from(targetAppointment);
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param doctorId    - Идентификатор доктора
     * @param patientId   - Идентификатор пациента
     * @param timeSlotId  - Идентификатор временного слота
     * @param appointment - Объект записи на прием
     * @return AppointmentDto
     */
    @Override
    public AppointmentDto create(Long doctorId, Long patientId, Long timeSlotId, AppointmentDto appointment) {
        try {
            Doctor targetDoctor = doctorRepository
                    .findById(doctorId).orElseThrow(NotFoundException::new);
            Patient targetPatient = patientRepository
                    .findById(patientId).orElseThrow(NotFoundException::new);
            TimeSlot targetTimeSlot = timeSlotRepository
                    .findById(timeSlotId)
                    .orElseThrow(NotFoundException::new);

            targetTimeSlot.setAvailability(false);

            return from(appointmentRepository.save(
                        Appointment.builder()
                                .doctor(targetDoctor)
                                .patient(targetPatient)
                                .timeSlot(targetTimeSlot)
                                .serviceType(appointment.getServiceType())
                                .hasRemoved(false)
                                .build()
                    ));
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param appointmentId  - Идентификатор приема
     * @param editedAppointmentEntity - Объект записи на прием
     * @return AppointmentDto
     */
    @Override
    public AppointmentDto update(
            Long appointmentId, AppointmentDto editedAppointmentEntity
    ) {
        try {
            Appointment appointment = appointmentRepository
                    .findById(appointmentId).orElseThrow(NotFoundException::new);
            TimeSlot timeSlot = timeSlotRepository
                    .findById(editedAppointmentEntity.getId()).orElseThrow(NotFoundException::new);

            timeSlot.setAvailability(false);

            appointment.setTimeSlot(timeSlot);
            appointment.setServiceType(editedAppointmentEntity.getServiceType());

            return from(appointmentRepository.save(appointment));
        }  catch (CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param id - Идентификатор приема
     */
    @Override
    public void deleteById(Long id) {
        try {
            AppointmentDto appointment = findById(id);

            if (appointment == null) throw new CustomException(ResultsMessages.APPOINTMENT_NOT_FOUND);

            TimeSlot timeSlot = timeSlotRepository.getReferenceById(appointment.getTimeSlotId());

            if (
                    timeSlot.getDate().isAfter(LocalDate.now())
                            && timeSlot.getStartTime().isAfter(LocalTime.now())
            ) {
                timeSlot.setAvailability(true);
            }

            appointmentRepository.deleteById(id);
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @return ResultsMessages
     */
    @Override
    public ResultsMessages deleteAll() {
        try {
            appointmentRepository.findAll()
                            .stream().peek(currentAppointment -> {
                        TimeSlot timeSlot = timeSlotRepository.findById(
                                currentAppointment.getTimeSlot().getId()
                        ).orElseThrow(NotFoundException::new);

                        if (
                                timeSlot.getDate().isAfter(LocalDate.now())
                                        && timeSlot.getStartTime().isAfter(LocalTime.now())
                        ) {
                            timeSlot.setAvailability(true);
                        }
                    }).close();

            appointmentRepository.deleteAll();

            return ResultsMessages.APPOINTMENTS_LIST_REMOVE_SUCCESS;
        } catch(CustomException err) {
            err.getStackTrace();
            return ResultsMessages.APPOINTMENTS_LIST_REMOVE_ERROR;
        }
    }

    /**
     * @param id - Идентификатор приема
     */
    @Override
    public void softDeleteById(Long id) {
        try {
            Appointment targetAppointment = appointmentRepository.findById(id).orElseThrow(NotFoundException::new);
            TimeSlot timeSlot = timeSlotRepository.getReferenceById(targetAppointment.getTimeSlot().getId());

            if (
                    timeSlot.getDate().isAfter(LocalDate.now())
                            && timeSlot.getStartTime().isAfter(LocalTime.now())
            ) {
                timeSlot.setAvailability(true);
            }

            targetAppointment.setHasRemoved(true);
            from(appointmentRepository.save(targetAppointment));
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param doctorId - Идентификатор доктора
     * @return List<AppointmentDto>
     */
    @Override
    public List<AppointmentDto> findAllByDoctorId(Long doctorId) {
        try {
            return appointmentRepository
                    .findAllByDoctorId(doctorId)
                    .stream()
                    .map(AppointmentDto::from)
                    .toList();
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param patientId - Идентификатор пациента
     * @return List<AppointmentDto>
     */
    @Override
    public List<AppointmentDto> findAllByPatientId(Long patientId) {
        try {
            return appointmentRepository
                    .findAllByPatientId(patientId)
                    .stream()
                    .map(AppointmentDto::from)
                    .toList();
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param timeSlotId - Идентификатор временного слота
     * @return AppointmentDto
     */
    @Override
    public Appointment findByTimeSlotId(Long timeSlotId) {
        try {
            return appointmentRepository.findByTimeSlotId(timeSlotId);
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param doctorId  - Идентификатор доктора
     * @param localDate - Целевая дата приема
     * @return List<Appointment>
     */
    @Override
    public List<Appointment> findAllByDoctorIdAndDate(Long doctorId, LocalDate localDate) {
        try {
            return appointmentRepository.findAllByDoctorIdAndDate(doctorId, localDate);
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param patientId - Идентификатор пациента
     * @param localDate - Целевая дата приема
     * @return List<Appointment>
     */
    @Override
    public List<Appointment> findAllByPatientIdAndDate(Long patientId, LocalDate localDate) {
        try {
            return appointmentRepository.findAllByPatientIdAndDate(patientId, localDate);
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }

    /**
     * @param doctorId  - Идентификатор пациента
     * @param patientId - Целевая дата приема
     * @return List<Appointment>
     */
    @Override
    public List<Appointment> findAllByDoctorIdAndPatientId(Long doctorId, Long patientId) {
        try {
            return appointmentRepository.findAllByDoctorIdAndPatientId(doctorId, patientId);
        } catch(CustomException err) {
            err.getStackTrace();
            throw new CustomException(err.getMessage());
        }
    }
}
