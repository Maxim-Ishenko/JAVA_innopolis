package ru.innopolis.attestation03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.innopolis.attestation03.dto.AppointmentDto;
import ru.innopolis.attestation03.models.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query(value="SELECT appointments FROM Appointment appointments WHERE appointments.hasRemoved = false")
    List<Appointment> findAllNotRemovedAppointments();

    List<Appointment> findAllByPatientId(Long patientId);

    List<Appointment> findAllByDoctorId(Long doctorId);

    List<Appointment> findAllByDoctorIdAndPatientId(Long doctorId, Long patientId);

    Appointment findByTimeSlotId(Long timeSlotId);

    List<Appointment> findAllByDoctorIdAndDate(Long doctorId, LocalDate localDate);

    List<Appointment> findAllByPatientIdAndDate(Long patientId, LocalDate localDate);
}
