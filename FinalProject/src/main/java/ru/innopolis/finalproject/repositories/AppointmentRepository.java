package ru.innopolis.finalproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.innopolis.finalproject.dto.AppointmentDto;
import ru.innopolis.finalproject.models.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query(value="SELECT appointments FROM Appointment appointments WHERE appointments.hasRemoved = false")
    List<Appointment> findAllNotRemovedAppointments();

    List<Appointment> findAllByPatientId(Long patientId);

    List<Appointment> findAllByDoctorId(Long doctorId);

    List<Appointment> findAllByDoctorIdAndPatientId(Long doctorId, Long patientId);

    Appointment findByTimeSlotId(Long timeSlotId);

    List<Appointment> findAllByDoctorIdAndTimeSlotDate(Long doctorId, LocalDate localDate);

    List<Appointment> findAllByPatientIdAndTimeSlotDate(Long patientId, LocalDate localDate);
}
